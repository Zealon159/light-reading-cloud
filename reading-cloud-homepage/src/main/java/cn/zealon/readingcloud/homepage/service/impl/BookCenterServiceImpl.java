package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.cache.RedisBookKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.homepage.service.BookCenterService;
import cn.zealon.readingcloud.common.pojo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zealon.readingcloud.book.feign.client.BookClient;

/**
 * 图书中心服务
 * @author: zealon
 * @since: 2019/7/4
 */
@Service
public class BookCenterServiceImpl implements BookCenterService {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private RedisService redisService;

    @Override
    public Book getBookById(String bookId) {
        String key = RedisBookKey.BookCenter.getFeignClientBookKey(bookId);
        Book book = this.redisService.getCache(key, Book.class);
        if (book != null) {
            return book;
        }

        // 图书中心服务获取
        book = bookClient.getBookById(bookId).getData();
        if (book != null) {
            this.redisService.setExpireCache(key, book, RedisExpire.HOUR);
        }
        return book;
    }
}
