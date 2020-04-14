package cn.zealon.readingcloud.account.vo;

import lombok.Data;

/**
 * 认证VO
 * @author: zealon
 * @since: 2020/4/14
 */
@Data
public class AuthVO {
    private String token;
    private UserVO user;
}
