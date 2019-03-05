package cn.zealon.user.client;

import cn.zealon.user.interfaces.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", fallback = UserFeignClientFallback.class)
public interface UserFeignClient extends UserService {

}
