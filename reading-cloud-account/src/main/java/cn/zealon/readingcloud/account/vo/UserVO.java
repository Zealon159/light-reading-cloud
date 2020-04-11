package cn.zealon.readingcloud.account.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * @author: zealon
 * @since: 2020/4/11
 */
@Data
public class UserVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    private String uuid;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 中文名
     */
    private String nickName;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 头像
     */
    private String headImgUrl;
}
