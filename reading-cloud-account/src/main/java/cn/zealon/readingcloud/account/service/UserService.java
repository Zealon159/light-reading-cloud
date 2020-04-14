package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.account.bo.UserBO;
import cn.zealon.readingcloud.account.vo.AuthVO;
import cn.zealon.readingcloud.common.result.Result;

/**
 * 用户服务
 * @author: zealon
 * @since: 2020/4/11
 */
public interface UserService {

    /**
     * 注册账户
     * @param userBO
     * @return
     */
    Result register(UserBO userBO);

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    Result<AuthVO> login(String loginName, String password);

    /**
     * 退出登录
     * @param loginName
     * @return
     */
    Result logout(String loginName);

    /**
     * 修改账户
     * @param user
     * @return
     */
    Result update(UserBO userBO);

    /**
     * 修改密码
     * @param user
     * @return
     */
    Result updatePassword(UserBO userBO);
}
