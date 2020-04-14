package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 喜欢看接口
 * @author: zealon
 * @since: 2020/4/14
 */
@Api(description = "喜欢看接口")
@RestController
@RequestMapping("account/like-see")
public class LikeSeeController {

    @PostMapping("/click")
    public Result click(@RequestHeader("id") Integer userId, String bookId){
        System.out.println(userId + "," + bookId);
        return ResultUtil.success("yes, you like it. ");
    }
}
