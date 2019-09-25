package cn.zealon.user.controller;

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

@RestController
@RequestMapping("user1")
public class Controller {

    @RequestMapping(value = "/get-info", method = RequestMethod.GET)
    public String hello(){

        return "admin,hello";
    }
}
 */