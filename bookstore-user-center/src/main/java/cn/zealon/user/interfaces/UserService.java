package cn.zealon.user.interfaces;

import cn.zealon.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {

    /**
     * hello
     * @param userName
     * @return
     */
    @GetMapping(value = "/user-center/user/hello/{userName}")
    String sayHello(@PathVariable("userName") String userName);

    /**
     * 按id查询
     * @param uid
     * @return
     */
    @GetMapping(value = "/user-center/user/find-user/{uid}")
    User findById(@PathVariable("uid") Long uid) ;
}
