package cn.zealon.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
@Service
public class UserService {

    /**
     * 注入restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    public String fallback(String userName){

        System.out.println("业务服务异常，进入快速失败！");
        return userName+"，业务服务异常，进入快速失败！";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String sayHello(String userName){
        System.out.println("使用restTemplate调用微服务接口");
        int a = Integer.parseInt(userName+9);
        System.out.println(a);

        // 使用restTemplate调用微服务接口
        String url = "http://user-service/user-center/user/hello/"+userName;
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
