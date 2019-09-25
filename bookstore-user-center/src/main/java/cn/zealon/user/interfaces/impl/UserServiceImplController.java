package cn.zealon.user.interfaces.impl;

import cn.zealon.user.entity.User;
import cn.zealon.user.interfaces.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
@RestController
public class UserServiceImplController implements UserService {

    @Override
    public String sayHello(@PathVariable String userName) {
        return "Hello,"+userName;
    }

    @Override
    public User findById(@PathVariable Long uid) {
        System.out.println("********************* uid:"+uid);
        if (uid<0){
            // 构造异常
            Long a = uid+Integer.parseInt("a");
        }
        User user = new User();
        String userName = "User_"+uid;
        user.setUid(uid);
        user.setUserName(userName);
        return user;
    }
}
