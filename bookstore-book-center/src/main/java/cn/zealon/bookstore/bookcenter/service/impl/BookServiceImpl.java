package cn.zealon.bookstore.bookcenter.service.impl;

import cn.zealon.bookstore.bookcenter.dao.BookMapper;
import cn.zealon.bookstore.bookcenter.service.BookService;
import cn.zealon.bookstore.common.cache.RedisConstant;
import cn.zealon.bookstore.common.cache.RedisService;
import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.result.Result;
import cn.zealon.bookstore.common.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public Result getBookById(String bookId) {
        String key = RedisConstant.Book.getBookKey(bookId);
        Book book = this.redisService.getCache(key, Book.class);
        if (null == book) {
            book = this.bookMapper.selectByBookId(bookId);
            if (null != book) {
                this.redisService.setExpireCache(key, book, RedisConstant.Expire.MINUTE_THIRTY);
            }
        }
        return ResultUtil.success(book);
    }

}
