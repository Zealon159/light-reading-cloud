package cn.zealon.bookstore.homepage.service;

import cn.zealon.bookstore.homepage.vo.BookVO;
import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 图书
 * @author: tangyl
 * @since: 2019/7/4
 */
public interface BookService {

    /**
     * 获取图书详情
     * @param bookId
     * @return
     */
    BookVO getBookDetailsById(String bookId);

    Result getBookById(String bookId);

    ResponseEntity<byte[]> getBookList();

    List<Book> getBookList2();
}
