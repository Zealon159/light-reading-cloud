package cn.zealon.user.client;

import cn.zealon.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public String sayHello(String userName) {
        return "失败了啊，进入失败方法。";
    }

    @Override
    public User findById(Long uid) {
        User user = new User();
        user.setUid(-1L);
        user.setUserName("default username");
        return user;
    }
}
