package cn.zealon.bookstore.homepage.service.impl;

import cn.zealon.bookstore.homepage.service.BookService;
import cn.zealon.bookstore.homepage.vo.BookVO;
import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import cn.zealon.bookstore.bookcenter.feign.client.BookClient;
import java.util.List;

/**
 * 图书
 * @author: tangyl
 * @since: 2019/7/4
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookClient bookClient;

    @Override
    public BookVO getBookDetailsById(String bookId) {
        BookVO bookVO = new BookVO();
        return bookVO;
    }

    @Override
    public Result getBookById(String bookId) {
        return bookClient.getBookById(bookId);
    }

    @Override
    public ResponseEntity<byte[]> getBookList() {
        ResponseEntity<byte[]> bookList = bookClient.getBookList();
        System.out.println(bookList.getBody().length);
        return bookList;
    }

    @Override
    public List<Book> getBookList2() {
        List<Book> bookList2 = bookClient.getBookList2();
        byte[] bytes = bookList2.toString().getBytes();
        System.out.println(bytes.length);
        return bookList2;
    }
}
