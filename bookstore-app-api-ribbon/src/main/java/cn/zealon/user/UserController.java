package cn.zealon.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 注入restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/get-info", method=RequestMethod.GET)
    public String helloCustomer(){

        System.out.println("使用restTemplate调用微服务接口");

        // 使用restTemplate调用微服务接口
        return restTemplate.getForEntity("http://user-service/user/get-info", String.class).getBody();

    }
}
