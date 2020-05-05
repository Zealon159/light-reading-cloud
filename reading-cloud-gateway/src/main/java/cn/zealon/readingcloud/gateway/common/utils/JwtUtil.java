package cn.zealon.readingcloud.gateway.common.utils;

import cn.zealon.readingcloud.common.pojo.account.User;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import static cn.zealon.readingcloud.common.constant.JwtConstant.SECRET_KEY;

/**
 * JWT工具类
 * @author: zealon
 * @since: 2020/4/14
 */
public class JwtUtil {

    /**
     * 身份认证
     * @param jwt 令牌
     * @return 成功状态返回200，其它均为失败
     */
    public static Result<User> validationToken(String jwt) {
        try {
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
            User user = new User();
            user.setUuid(claims.get("uuid").toString());
            user.setLoginName(claims.get("loginName").toString());
            user.setNickName(claims.get("nickName").toString());
            if (claims.get("phoneNumber") != null) {
                user.setPhoneNumber(claims.get("phoneNumber").toString());
            }
            user.setId(Integer.parseInt(claims.get("id").toString()));
            user.setHeadImgUrl(claims.get("headImgUrl").toString());
            return ResultUtil.success(user);
        } catch (ExpiredJwtException e) {
            // 已过期令牌
            return ResultUtil.authExpired();
        } catch (SignatureException e) {
            // 伪造令牌
            return ResultUtil.unAuthorized();
        } catch (Exception e) {
            // 系统错误
            return ResultUtil.unAuthorized();
        }
    }

    public static void main(String[] args){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODkxMTcyNzcsImxvZ2luTmFtZSI6IjAwIiwibmlja05hbWUiOiIwMCIsImhlYWRJbWdVcmwiOiJodHRwOi8vcTk0aXN3ejM3LmJrdC5jbG91ZGRuLmNvbS9kZWZhdWx0LmpwZyIsInV1aWQiOiIyMjk0YzBiNmM2Y2Y0MjI1YWRmNTRhNzE2YzNmNDNiZSJ9.diWIKZPpefc5nfGa7S2u2eYIWbF1TYO-MUTHqQCtCFs";
        Result<User> jwtValid = validationToken(jwt);
        if (jwtValid.getCode() == 200) {
            System.out.println(jwtValid.getData().toString());
        }
    }
}
