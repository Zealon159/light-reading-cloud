package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.BookMapper;
import cn.zealon.readingcloud.book.service.BookService;
import cn.zealon.readingcloud.book.vo.BookVO;
import cn.zealon.readingcloud.common.cache.RedisBookKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.enums.BookCategoryEnum;
import cn.zealon.readingcloud.common.enums.BookSerialStatusEnum;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import org.springframework.beans.BeanUtils;
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
                this.redisService.setExpireCache(key, book, RedisExpire.HOUR);
            }
        }
        return ResultUtil.success(book);
    }

    @Override
    public Result<BookVO> getBookDetails(String bookId) {
        Book book = this.getBookById(bookId).getData();
        if (book == null) {
            return ResultUtil.notFound().buildMessage("找不到"+bookId+"这本书哦！");
        }

        BookVO vo = new BookVO();
        BeanUtils.copyProperties(book, vo);
        // 分类
        String categoryName = BookCategoryEnum.values()[book.getDicCategory() - 1].getName();
        vo.setCategoryName(categoryName);
        // 连载状态
        String serialStatusName = BookSerialStatusEnum.values()[book.getDicSerialStatus() - 1].getName();
        vo.setSerialStatusName(serialStatusName);
        return ResultUtil.success(vo);
    }
}
