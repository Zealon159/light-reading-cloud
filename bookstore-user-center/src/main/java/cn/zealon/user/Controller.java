package cn.zealon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tangyl
 * @Date: 2019/2/27
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class Controller {
    /**
     * 注入发现客户端
     */
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){

        return "hello";
    }
}
