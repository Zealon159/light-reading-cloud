package cn.zealon.bookstore.app.api.feign;

import cn.zealon.bookstore.app.api.feign.config.BookChapterHystrixCommandConfig;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: tangyl
 * @since: 2019/9/25
 */
@FeignClient(contextId = "book-chapter",name = "bookstore-book-center", fallbackFactory = BookChapterFeignClientFallback.class
        ,configuration = BookChapterHystrixCommandConfig.class)
public interface BookChapterFeignClient {

    @RequestMapping("book/chapter/getChapterContent")
    String getChapterContent(
            @RequestParam("bookId") String bookId, @RequestParam("chapterId") String chapterId);
}

@Component
class BookChapterFeignClientFallback implements FallbackFactory<BookChapterFeignClient> {

    @Override
    public BookChapterFeignClient create(Throwable cause) {
        return (bookId, chapterId) -> {
            System.out.println("currentThread:"+Thread.currentThread().getName());
            return "defualt content.";
        };
    }
}
