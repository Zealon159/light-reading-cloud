package cn.zealon.bookstore.bookcenter.service.impl;

import cn.zealon.bookstore.bookcenter.dao.BookChapterMapper;
import cn.zealon.bookstore.bookcenter.service.BookChapterService;
import cn.zealon.bookstore.bookcenter.service.BookService;
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

    @Override
    public Result getBookChapterListByBookId(String bookId) {
        Book book = (Book) bookService.getBookById(bookId).getData();
        if (book == null || !book.getOnlineStatus()) {
            ResultUtil.success(null);
        }
        List<BookChapter> chapters = null;
        chapters = this.bookChapterMapper.findPageWithResult(book.getId());
        return ResultUtil.success(chapters);
    }

    @Override
    public Result getChapterById(Integer chapterId) {
        BookChapter chapter = null;
        chapter = this.bookChapterMapper.selectById(chapterId);
        return ResultUtil.success(chapter);
    }
}
