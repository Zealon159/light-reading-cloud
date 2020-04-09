package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.BookMapper;
import cn.zealon.readingcloud.book.service.BookService;
import cn.zealon.readingcloud.common.cache.RedisBookKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图书服务
 * @author: tangyl
 * @since: 2019/7/4
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Result<Book> getBookById(String bookId) {
        String key = RedisBookKey.getBookKey(bookId);
        Book book = this.redisService.getCache(key, Book.class);
        if (null == book) {
            book = this.bookMapper.selectByBookId(bookId);
            if (null != book) {
                this.redisService.setExpireCache(key, book, RedisExpire.MINUTE_THIRTY);
            }
        }
        return ResultUtil.success(book);
    }

}
