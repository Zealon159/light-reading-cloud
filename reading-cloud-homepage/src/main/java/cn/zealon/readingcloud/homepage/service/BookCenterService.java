package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.homepage.vo.BookVO;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 图书中心服务
 * @author: zealon
 * @since: 2019/7/4
 */
public interface BookCenterService {

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
