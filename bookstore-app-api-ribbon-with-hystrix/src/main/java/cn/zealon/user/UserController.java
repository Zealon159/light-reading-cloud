package cn.zealon.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tangyl
 * @Date: 2019/3/1
 * @Version: 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/say-hello/{userName}", method=RequestMethod.GET)
    public String helloCustomer(@PathVariable String userName){
        return this.userService.sayHello(userName);

    }


}
