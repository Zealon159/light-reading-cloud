package cn.zealon.bookstore.bookcenter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心API
 * @Author: tangyl
 * @Date: 2019/2/27
 * @Version: 1.0
 */
@RestController
@RefreshScope
@RequestMapping("api")
public class TestController {

    @Value("${from}")
    private String fromValue;

    /**
     * 返回配置文件中的值
     */
    @GetMapping("/from")
    public String returnFormValue(){
        return fromValue;
    }
}
