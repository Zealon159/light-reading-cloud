package cn.zealon.readingcloud.account.feign.client;

import cn.zealon.readingcloud.account.feign.fallback.LikeSeeClientFallBack;
import cn.zealon.readingcloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 喜欢看客户端feign接口
 * @author: zealon
 * @since: 2019/7/4
 */
@FeignClient(contextId = "like", name = "light-reading-cloud-account", fallbackFactory = LikeSeeClientFallBack.class)
public interface LikeSeeClient {

    @GetMapping("/account/like-see/get-count")
    Result<Integer> getBookLikesCount(@RequestParam("bookId") String bookId);

}