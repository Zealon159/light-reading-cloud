package cn.zealon.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
/*
@FeignClient(name = "integral", fallback = UserIntegralClientFallback.class)
@RequestMapping("integral")
public interface UserIntegralClient {

    @RequestMapping("/get/{uid}")
    String getIntegral(@RequestParam("uid") Long uid);
}
*/
