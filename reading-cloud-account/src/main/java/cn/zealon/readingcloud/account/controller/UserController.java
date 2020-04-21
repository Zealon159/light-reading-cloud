package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.bo.UserBO;
import cn.zealon.readingcloud.account.service.UserService;
import cn.zealon.readingcloud.account.vo.AuthVO;
import cn.zealon.readingcloud.common.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 用户接口
 * @author: zealon
 * @since: 2020/4/11
 */
@Api(description = "用户服务接口")
@RestController
@RequestMapping("account/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = Result.class)})
    @PostMapping("/register")
    public Result register(@RequestBody UserBO userBO) {
        return this.userService.register(userBO);
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "loginName", value = "登录名", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "password", value = "登录密码", required = true, dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AuthVO.class)})
    @PostMapping("/login")
    public Result<AuthVO> login(@RequestBody Map<String,Object> params) {
        String loginName = params.get("loginName").toString();
        String password = params.get("password").toString();
        return this.userService.login(loginName, password);
    }
}