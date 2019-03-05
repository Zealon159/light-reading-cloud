package cn.zealon.user.controller;

import cn.zealon.user.client.UserFeignClient;
import cn.zealon.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserFeignClient userFeignClient;

    /*@GetMapping("/hello")
    public String findByIdFeign() {
        return this.userFeignClient.helloFeign();
    }*/

    @GetMapping(value = "/say-hello/{userName}")
    public String sayHello(@PathVariable("userName") String userName){
        return this.userFeignClient.sayHello(userName);
    }

    @GetMapping(value = "/find-user/{uid}")
    public User findUserById(@PathVariable("uid") Long uid) {
        return this.userFeignClient.findById(uid);
    }
}
