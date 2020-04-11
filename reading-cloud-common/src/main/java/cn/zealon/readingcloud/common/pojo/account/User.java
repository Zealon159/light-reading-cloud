package cn.zealon.readingcloud.common.pojo.account;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author: zealon
 * @since: 2020/4/10
 */
@Data
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 唯一id
     */
    private String uuid;

    /**
     * 密码
     */
    private String userPwd;

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

    private String headImgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
