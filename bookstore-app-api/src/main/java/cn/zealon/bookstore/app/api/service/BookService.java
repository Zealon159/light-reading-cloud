package cn.zealon.bookstore.app.api.service;

import cn.zealon.bookstore.app.api.vo.BookVO;
import cn.zealon.bookstore.common.model.NomalBook;
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

    NomalBook getBookById(String bookId);

    ResponseEntity<byte[]> getBookList();

    List<NomalBook> getBookList2();
}
