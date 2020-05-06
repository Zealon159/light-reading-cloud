package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.bo.UserBookshelfBO;
import cn.zealon.readingcloud.account.dao.UserBookshelfMapper;
import cn.zealon.readingcloud.account.service.UserBookshelfService;
import cn.zealon.readingcloud.account.service.task.UserBookshelfTask;
import cn.zealon.readingcloud.account.vo.UserBookshelfVO;
import cn.zealon.readingcloud.book.feign.client.BookClient;
import cn.zealon.readingcloud.common.pojo.account.UserBookshelf;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 书架服务
 * @author: zealon
 * @since: 2020/4/13
 */
@Service
public class UserBookshelfServiceImpl implements UserBookshelfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBookshelfServiceImpl.class);

    @Autowired
    private UserBookshelfMapper bookshelfMapper;

    /** 书架同步任务线程池 */
    @Autowired
    private ExecutorService userBookshelfQueueThreadPool;

    /** 图书资源中心feign接口 */
    @Autowired
    private BookClient bookClient;

    @Override
    public Result syncUserBookshelf(Integer userId, UserBookshelfBO bookshelfBO) {
        UserBookshelf bookshelf = new UserBookshelf();
        BeanUtils.copyProperties(bookshelfBO, bookshelf);
        bookshelf.setLastReadTime(System.currentTimeMillis());

        // 异步处理同步任务
        UserBookshelfTask task = new UserBookshelfTask(bookshelfBO.getSyncType(), bookshelf, this.bookshelfMapper, userId);
        this.userBookshelfQueueThreadPool.execute(task);
        return ResultUtil.success();
    }

    @Override
    public Result getUserBookshelf(Integer userId) {
        List<UserBookshelf> pageWithResult = this.bookshelfMapper.findPageWithResult(userId);
        List<UserBookshelfVO> bookshelfs = new ArrayList<>();
        for (int i = 0; i < pageWithResult.size(); i++) {
            UserBookshelf bookshelf = pageWithResult.get(i);
            Book book = this.bookClient.getBookById(bookshelf.getBookId()).getData();
            if (book != null) {
                UserBookshelfVO vo = new UserBookshelfVO();
                BeanUtils.copyProperties(bookshelf, vo);
                vo.setBookName(book.getBookName());
                vo.setAuthorName(book.getAuthorName());
                vo.setImgUrl(book.getImgUrl());
                bookshelfs.add(vo);
            }
        }
        return ResultUtil.success(bookshelfs);
    }

    @Override
    public Result<Integer> userBookshelfExistBook(Integer userId, String bookId) {
        int result = 0;
        try {
            result = this.bookshelfMapper.selectCountByUserAndBookId(userId, bookId);
        } catch (Exception ex){
            LOGGER.error("查询图书是否在用户书架里异常：{}", ex);
        }
        return ResultUtil.success(result);
    }
}
