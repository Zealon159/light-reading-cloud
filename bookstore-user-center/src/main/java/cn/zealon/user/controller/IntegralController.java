package cn.zealon.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分中心
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
@RestController
@RequestMapping("integral")
public class IntegralController {
    @RequestMapping(value = "/get/{uid}")
    public String getIntegral(@PathVariable Long uid){

        return "获取["+uid+"]的积分服务.";
    }
}
