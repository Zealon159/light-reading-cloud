package cn.zealon.bookstore.bookcenter.feign.client;

import cn.zealon.bookstore.common.model.NomalBook;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 图书客户端feign接口
 * @author: tangyl
 * @since: 2019/7/4
 */
@FeignClient(name = "bookstore-book-center",fallbackFactory = BookClientFallBack.class)
public interface BookClient {

    @RequestMapping("/book/getBookById")
    NomalBook getBookById(@RequestParam("bookId") String bookId);
   // ResponseEntity<byte[]> getBookById(@RequestParam("bookId") String bookId);

    @RequestMapping("/book/getBookList")
    ResponseEntity<byte[]> getBookList();

    @RequestMapping("/book/getBookList")
    List<NomalBook> getBookList2();

}

@Component
class BookClientFallBack implements FallbackFactory<BookClient> {

    @Override
    public BookClient create(Throwable cause) {
        return new BookClient() {
            @Override
            public NomalBook getBookById(String bookId) {
                System.out.println(cause.getMessage());
                NomalBook book = new NomalBook();
                book.setBookName("Default Book.");
                book.setBookId("0");
                return book;
            }

            @Override
            public ResponseEntity<byte[]> getBookList() {
                return null;
            }

            @Override
            public List<NomalBook> getBookList2() {
                return null;
            }
        };
    }
}
