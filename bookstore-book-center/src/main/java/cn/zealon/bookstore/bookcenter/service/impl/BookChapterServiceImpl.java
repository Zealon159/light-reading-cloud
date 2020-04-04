package cn.zealon.bookstore.bookcenter.service.impl;

import cn.zealon.bookstore.bookcenter.dao.BookChapterMapper;
import cn.zealon.bookstore.bookcenter.service.BookChapterService;
import cn.zealon.bookstore.bookcenter.service.BookService;
import cn.zealon.bookstore.common.cache.RedisConstant;
import cn.zealon.bookstore.common.cache.RedisService;
import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.pojo.book.BookChapter;
import cn.zealon.bookstore.common.result.Result;
import cn.zealon.bookstore.common.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 图书章节服务
 * @author: zealon
 * @since: 2019/9/25
 */
@Service
public class BookChapterServiceImpl implements BookChapterService {

    @Autowired
    private BookChapterMapper bookChapterMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisService redisService;

    @Override
    public Result getBookChapterListByBookId(String bookId) {
        Book book = (Book) bookService.getBookById(bookId).getData();
        if (null == book) {
            return ResultUtil.notFound().buildMessage("该书不存在与本系统哦！");
        }

        String key = RedisConstant.Book.getBookChapterListKey(bookId);
        List<BookChapter> chapters = this.redisService.getCacheForList(key, BookChapter.class);
        if (null == chapters || chapters.size() == 0) {
            chapters = this.bookChapterMapper.findPageWithResult(book.getId());
            if (chapters.size() > 0) {
                this.redisService.setExpireCache(key, chapters, RedisConstant.Expire.HOUR);
            }
        }
        return ResultUtil.success(chapters);
    }

    @Override
    public Result getChapterById(String bookId, Integer chapterId) {
        BookChapter chapter;
        String key = RedisConstant.Book.getBookChapterKey(bookId);
        String field = chapterId.toString();
        chapter = this.redisService.getHashVal(key, field, BookChapter.class);
        if (chapter == null) {
            chapter = this.bookChapterMapper.selectById(chapterId);
            if (chapter != null) {
                this.redisService.setHashValExpire(key, field ,chapter, RedisConstant.Expire.HOUR);
            }
        }
        return ResultUtil.success(chapter);
    }
}
