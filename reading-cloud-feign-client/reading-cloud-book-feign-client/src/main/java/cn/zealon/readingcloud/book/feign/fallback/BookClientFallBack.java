package cn.zealon.readingcloud.book.feign.fallback;

import cn.zealon.readingcloud.book.feign.client.BookClient;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 图书客户端feign接口快速失败
 *
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class BookClientFallBack implements FallbackFactory<BookClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookClientFallBack.class);

    @Override
    public BookClient create(Throwable cause) {
        return new BookClient() {
            @Override
            public Result<Book> getBookById(String bookId) {
                LOGGER.error("获取图书[{}]失败：{}", bookId, cause.getMessage());
                return ResultUtil.success(null);
            }
        };
    }
}
