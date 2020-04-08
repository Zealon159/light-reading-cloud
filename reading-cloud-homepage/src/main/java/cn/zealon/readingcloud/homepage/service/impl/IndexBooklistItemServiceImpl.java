package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.cache.RedisHomepageKey;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.pojo.index.IndexBooklist;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.homepage.service.BookCenterService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistItemService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistService;
import cn.zealon.readingcloud.homepage.vo.BooklistBookVO;
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

    /**
     * 书单更多分页接口
     * @param booklistId            书单ID
     * @param page                  页数
     * @param limit                 每页数量
     * @return
     */
    @Override
    public Result getBooklistPagingBooks(Integer booklistId, Integer page, Integer limit) {
        return null;
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

        // 获取随机书单
        List<BooklistBookVO> randomBooks = this.getBooklistRandomBooks(booklistId, booklist.getBookIds(), booklist.getShowNumber(), clientRandomNumber);
        return ResultUtil.success(randomBooks);
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
     * @return
     */
    @Override
    public List<BooklistBookVO> getBooklistRandomBooks(Integer booklistId, String bookIds, Integer showNumber, Integer clientRandomNumber) {
        // 随机获取的书单
        Set<String> randomBooks = new HashSet<>();
        String[] bookIdArray = bookIds.split(",");
        if (bookIdArray.length < showNumber) {
            // 若数据库中不够随机，直接返回顺序书单
            return this.getBooklistOrderBooks(booklistId, bookIds, showNumber);
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

        String[] randomBookIdArray = (String[]) randomBooks.toArray();
        return this.getBooklistBookVOByBookIdArray(randomBookIdArray);
    }

    @Override
    public List<BooklistBookVO> getBooklistOrderBooks(Integer booklistId, String bookIds, Integer showNumber) {
        String[] bookIdArray = bookIds.split(",");
        return this.getBooklistBookVOByBookIdArray(bookIdArray);
    }

    /**
     * 获取书单VO
     * @param bookIdArray
     * @return
     */
    private List<BooklistBookVO> getBooklistBookVOByBookIdArray(String[] bookIdArray){
        List<BooklistBookVO> vos = new ArrayList<>();
        for (int i = 0; i < bookIdArray.length; i++) {
            String bookId = bookIdArray[i];
            BooklistBookVO vo = this.getBookVO(bookId);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 获取榜单图书VO
     * @param bookId
     * @return
     */
    private BooklistBookVO getBookVO(String bookId){
        Result result = this.bookCenterService.getBookById(bookId);
        if (result.getData() == null) {
            LOGGER.warn("图书资源中心获取Book:{}失败！返回了空数据", bookId);
            return null;
        }
        Book book = (Book) result.getData();
        BooklistBookVO vo = new BooklistBookVO();
        BeanUtils.copyProperties(book, vo);
        // todo 获取分类

        return vo;
    }

    /**
     * 获取客户端书单Id集合
     * @param booklistId
     * @return
     */
    private Set<String> getClientBookIds(Integer booklistId, Integer clientRandomNumber){
        // 客户端当前显示的书单
        String key = RedisHomepageKey.getBooklistRandomVoKey(booklistId);
        List<BooklistBookVO> clientBooks = null;
        try {
            clientBooks = this.redisService.getHashListVal(key, clientRandomNumber.toString(), BooklistBookVO.class);
        } catch (Exception ex){
            LOGGER.error("Redis获取客户端书单失败了！booklistId:{},clientRandomNumber:{}", ex, booklistId, clientRandomNumber);
        }
        return new HashSet(clientBooks);
    }
}
