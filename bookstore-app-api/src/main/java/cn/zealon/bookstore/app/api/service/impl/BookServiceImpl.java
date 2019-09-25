package cn.zealon.bookstore.app.api.service.impl;

import cn.zealon.bookstore.app.api.feign.BookFeignClient;
import cn.zealon.bookstore.app.api.service.BookService;
import cn.zealon.bookstore.app.api.vo.BookVO;
import cn.zealon.bookstore.bookcenter.feign.client.BookClient;
import cn.zealon.bookstore.common.model.NomalBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书
 * @author: tangyl
 * @since: 2019/7/4
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookFeignClient bookClient;

    @Override
    public BookVO getBookDetailsById(String bookId) {
        //NomalBook nomalBook = bookClient.getBookById(bookId);
        NomalBook bookById = bookClient.getBookById(bookId);
        System.out.println(bookById.getBookName());

        BookVO bookVO = new BookVO();
        //BeanUtils.copyProperties(nomalBook,bookVO);

        return bookVO;
    }

    @Override
    public NomalBook getBookById(String bookId) {
        return bookClient.getBookById(bookId);
    }

    @Override
    public ResponseEntity<byte[]> getBookList() {
        ResponseEntity<byte[]> bookList = bookClient.getBookList();
        System.out.println(bookList.getBody().length);
        return bookList;
    }

    @Override
    public List<NomalBook> getBookList2() {
        List<NomalBook> bookList2 = bookClient.getBookList2();
        byte[] bytes = bookList2.toString().getBytes();
        System.out.println(bytes.length);
        return bookList2;
    }
}
