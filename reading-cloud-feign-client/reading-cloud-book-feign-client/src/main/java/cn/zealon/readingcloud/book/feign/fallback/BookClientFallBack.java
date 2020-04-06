package cn.zealon.readingcloud.book.feign.fallback;

import cn.zealon.readingcloud.book.feign.client.BookClient;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 图书客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class BookClientFallBack implements FallbackFactory<BookClient> {

    @Override
    public BookClient create(Throwable cause) {
        return new BookClient() {
            @Override
            public Result getBookById(String bookId) {
                System.out.println(cause.getMessage());
                Book book = new Book();
                book.setBookName("Default Book.");
                book.setBookId("0");
                return ResultUtil.success(book);
            }

            @Override
            public ResponseEntity<byte[]> getBookList() {
                return null;
            }

            @Override
            public List<Book> getBookList2() {
                return null;
            }
        };
    }
}
