package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.UserLikeSeeMapper;
import cn.zealon.readingcloud.account.service.UserLikeSeeService;
import cn.zealon.readingcloud.account.service.task.LikeSeeClickTask;
import cn.zealon.readingcloud.book.feign.client.BookClient;
import cn.zealon.readingcloud.common.cache.RedisAccountKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.account.UserLikeSee;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.common.vo.SimpleBookVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 用户喜欢服务
 * @author: zealon
 * @since: 2020/4/15
 */
@Service
public class UserLikeSeeServiceImpl implements UserLikeSeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLikeSeeServiceImpl.class);

    @Autowired
    private UserLikeSeeMapper likeSeeMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private ExecutorService commonQueueThreadPool;

    @Override
    public Result likeSeeClick(Integer userId, String bookId, Integer value) {
        try{
            // 0取消喜欢， 1增加喜欢
            if (0 == value) {
                this.likeSeeMapper.deleteByUserIdAndBookId(userId, bookId);
            } else {
                UserLikeSee like = new UserLikeSee();
                like.setUserId(userId);
                like.setBookId(bookId);
                this.likeSeeMapper.insert(like);
            }

            // 更新缓存
            LikeSeeClickTask task = new LikeSeeClickTask(redisService, bookId, value);
            this.commonQueueThreadPool.execute(task);
        } catch (Exception ex){
            LOGGER.error("用户喜欢点击操作异常：{}",ex);
            return ResultUtil.fail();
        }
        return ResultUtil.success();
    }

    @Override
    public Result<Integer> getBookLikesCount(String bookId) {
        Integer likeCount = this.redisService.getHashVal(RedisAccountKey.ACCOUNT_CENTER_BOOK_LIKES_COUNT, bookId, Integer.class);
        if (likeCount == null) {
            likeCount = this.likeSeeMapper.findPageWithCount(bookId);
            this.redisService.setHashValExpire(RedisAccountKey.ACCOUNT_CENTER_BOOK_LIKES_COUNT, bookId, likeCount, RedisExpire.HOUR);
        }
        return ResultUtil.success(likeCount);
    }

    @Override
    public Result getUserLikeBookList(Integer userId, Integer page, Integer limit) {
        try {
            PageHelper.startPage(page, limit);
            Page<UserLikeSee> pageWithResult = (Page<UserLikeSee>) this.likeSeeMapper.findPageWithResult(userId);
            List<SimpleBookVO> books = new ArrayList<>();
            for (int i = 0; i < pageWithResult.size(); i++) {
                SimpleBookVO vo = new SimpleBookVO();
                UserLikeSee likeSee = pageWithResult.get(i);
                Book book = this.bookClient.getBookById(likeSee.getBookId()).getData();
                if (book != null) {
                    BeanUtils.copyProperties(book, vo);
                    books.add(vo);
                }
            }
            return ResultUtil.success(books);
        } catch (Exception ex) {
            LOGGER.error("获取用户[{}]喜欢书单异常：{}", userId, ex);
            return ResultUtil.fail();
        }
    }

    @Override
    public Result userLikeThisBook(Integer userId, String bookId) {
        int result = 0;
        try {
            result = this.likeSeeMapper.selectCountByUserAndBookId(userId, bookId);
        } catch (Exception ex){
            LOGGER.error("查询喜欢此书异常：{}", ex);
        }
        return ResultUtil.success(result);
    }
}
