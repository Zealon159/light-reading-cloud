package cn.zealon.readingcloud.account.feign.fallback;

import cn.zealon.readingcloud.account.feign.client.LikeSeeClient;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 喜欢看客户端feign接口快速失败
 * @author: zealon
 * @since: 2019/7/4
 */
@Component
public class LikeSeeClientFallBack implements FallbackFactory<LikeSeeClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LikeSeeClientFallBack.class);

    @Override
    public LikeSeeClient create(Throwable cause) {
        return new LikeSeeClient() {
            @Override
            public Result<Integer> getBookLikesCount(String bookId) {
                LOGGER.error("获取喜欢看[{}]数量失败：{}", bookId, cause.getMessage());
                return ResultUtil.success(0);
            }
        };
    }
}
