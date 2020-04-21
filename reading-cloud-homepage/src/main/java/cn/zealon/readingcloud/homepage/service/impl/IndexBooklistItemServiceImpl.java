package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.account.feign.client.LikeSeeClient;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisHomepageKey;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.enums.BookCategoryEnum;
import cn.zealon.readingcloud.common.enums.BookSerialStatusEnum;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.pojo.index.IndexBooklist;
import cn.zealon.readingcloud.common.pojo.index.IndexBooklistItem;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.common.utils.CommonUtil;
import cn.zealon.readingcloud.homepage.dao.IndexBooklistItemMapper;
import cn.zealon.readingcloud.homepage.service.BookCenterService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistItemService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistService;
import cn.zealon.readingcloud.homepage.vo.BooklistBookVO;
import cn.zealon.readingcloud.homepage.vo.IndexBooklistVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 书单图书服务
 * @author: zealon
 * @since: 2020/4/7
 */
@Service
public class IndexBooklistItemServiceImpl implements IndexBooklistItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexBooklistItemServiceImpl.class);

    @Autowired
    private IndexBooklistService indexBooklistService;

    @Autowired
    private BookCenterService bookCenterService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IndexBooklistItemMapper indexBooklistItemMapper;

    @Autowired
    private LikeSeeClient likeSeeClient;

    /**
     * 分页 - 书单更多接口
     * @param booklistId            书单ID
     * @param page                  页数
     * @param limit                 每页数量
     * @return
     */
    @Override
    public Result getBooklistPagingBooks(Integer booklistId, Integer page, Integer limit) {
        String key = RedisHomepageKey.getBooklistItemPagingKey(booklistId);
        // 使用页码+数量作为Hash的key，防止不同数量的分页页码冲突
        String field = page.toString() + limit;
        List<BooklistBookVO> list = this.redisService.getHashListVal(key, field, BooklistBookVO.class);
        if (CommonUtil.isEmpty(list)) {
            list = new ArrayList<>();
            PageHelper.startPage(page, limit);
            Page<IndexBooklistItem> pageList = (Page<IndexBooklistItem>) this.indexBooklistItemMapper.findPageWithResult(booklistId);
            for (int i = 0; i < pageList.size(); i++) {
                String bookId = pageList.get(i).getBookId();
                BooklistBookVO vo = this.getBookVO(bookId);
                if (vo != null) {
                    list.add(vo);
                }
            }

            if (list.size() > 0) {
                this.redisService.setHashValExpire(key, field, list, RedisExpire.HOUR_TWO);
            }
        }
        return ResultUtil.success(list);
    }

    /**
     * 书单换一换接口
     * @param booklistId            书单ID
     * @param clientRandomNumber    客户端当前随机编号
     * @return
     */
    @Override
    public Result getBooklistExchange(Integer booklistId, Integer clientRandomNumber) {
        IndexBooklist booklist = this.indexBooklistService.getIndexBooklistById(booklistId);
        if (booklist == null) {
            return ResultUtil.notFound().buildMessage("找不到此书单哦！");
        }

        // 缓存获取随机书单
        IndexBooklistVO randomIndexBooklistVO = this.indexBooklistService.getRandomIndexBooklistVO(booklist, clientRandomNumber);
        return ResultUtil.success(randomIndexBooklistVO);
    }

    /**
     * 随机获取书单book
     * <p>
     *     1.得到客户端随机编码的book
     *     2.随机获取榜单图书，排除客户端当前的book
     *     3.随机图书列表包括不能重复图书
     * </p>
     * @param booklistId            书单ID
     * @param bookIds               图书IDs
     * @param showNumber            显示数量
     * @param clientRandomNumber    客户端当前随机编号
     * @param showLikeCount         是否显示喜欢数
     * @return
     */
    @Override
    public List<BooklistBookVO> getBooklistRandomBooks(Integer booklistId, String bookIds, Integer showNumber, Integer clientRandomNumber, Boolean showLikeCount) {
        // 随机获取的书单
        Set<String> randomBooks = new HashSet<>();
        String[] bookIdArray = bookIds.split(",");
        if (bookIdArray.length < showNumber) {
            // 若数据库中不够随机，直接返回顺序书单
            return this.getBooklistOrderBooks(booklistId, bookIds, showNumber, showLikeCount);
        }

        // 客户端书单
        Set<String> clientBookIds = this.getClientBookIds(booklistId, clientRandomNumber);
        Random random = new Random();
        while (randomBooks.size() < showNumber) {
            // 得到随机图书ID，并排重，排重客户端书单
            int randomIndex = random.nextInt(bookIdArray.length);
            String bookId = bookIdArray[randomIndex];
            if (!clientBookIds.contains(bookId) && !randomBooks.contains(bookId)) {
                randomBooks.add(bookId);
            }
        }

        String[] randomBookIdArray = {};
        randomBookIdArray = randomBooks.toArray(randomBookIdArray);
        return this.getBooklistBookVOByBookIdArray(randomBookIdArray, showNumber, showLikeCount);
    }

    @Override
    public List<BooklistBookVO> getBooklistOrderBooks(Integer booklistId, String bookIds, Integer showNumber, Boolean showLikeCount) {
        String[] bookIdArray = bookIds.split(",");
        return this.getBooklistBookVOByBookIdArray(bookIdArray, showNumber, showLikeCount);
    }

    /**
     * 获取书单VO
     * @param bookIdArray
     * @param showNumber
     * @param showLikeCount
     * @return
     */
    private List<BooklistBookVO> getBooklistBookVOByBookIdArray(String[] bookIdArray, Integer showNumber, Boolean showLikeCount){
        List<BooklistBookVO> vos = new ArrayList<>();
        for (int i = 0; i < bookIdArray.length; i++) {
            String bookId = bookIdArray[i];
            BooklistBookVO vo = this.getBookVO(bookId);
            if (vo != null) {
                // 获取喜欢数
                if (showLikeCount) {
                    Integer likeCount = this.likeSeeClient.getBookLikesCount(bookId).getData();
                    vo.setLikeCount(likeCount);
                }
                vos.add(vo);
            }

            // VOS到达榜单定制数量，不再获取了
            if (vos.size() == showNumber) {
                break;
            }
        }
        return vos;
    }

    /**
     * 获取榜单图书VO
     * @param bookId
     * @return
     */
    private BooklistBookVO getBookVO(String bookId){
        Book book = this.bookCenterService.getBookById(bookId);
        if (book == null) {
            LOGGER.warn("图书资源中心获取Book:{}失败！返回了空数据", bookId);
            return null;
        }
        BooklistBookVO vo = new BooklistBookVO();
        BeanUtils.copyProperties(book, vo);
        // 分类
        String categoryName = BookCategoryEnum.values()[book.getDicCategory() - 1].getName();
        vo.setCategoryName(categoryName);
        // 连载状态
        String serialStatusName = BookSerialStatusEnum.values()[book.getDicSerialStatus() - 1].getName();
        vo.setSerialStatusName(serialStatusName);
        return vo;
    }

    /**
     * 获取客户端书单Id集合
     * @param booklistId
     * @return
     */
    private Set<String> getClientBookIds(Integer booklistId, Integer clientRandomNumber){
        if (clientRandomNumber == null) {
            return new HashSet<>();
        }

        // 客户端当前显示的书单
        String key = RedisHomepageKey.getBooklistRandomVoKey(booklistId);
        IndexBooklistVO booklistVO;
        List<BooklistBookVO> clientBooks = null;
        try {
            booklistVO = this.redisService.getHashVal(key, clientRandomNumber.toString(), IndexBooklistVO.class);
            if (booklistVO != null) {
                clientBooks = booklistVO.getBooks();
            }
        } catch (Exception ex){
            LOGGER.error("Redis获取客户端书单失败了！booklistId:{},clientRandomNumber:{}", ex, booklistId, clientRandomNumber);
        }
        if (clientBooks == null) {
            clientBooks = new ArrayList<>();
        }
        return new HashSet(clientBooks);
    }
}
