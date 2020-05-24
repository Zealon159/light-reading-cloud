package cn.zealon.readingcloud.account.common.utils;

import cn.zealon.readingcloud.common.utils.MD5Util;

/**
 * 用户工具类
 * @author: zealon
 * @since: 2020/4/11
 */
public class UserUtil {

    /**
     * 获取用户盐值，用于加密用户密码
     * @param loginName
     * @return
     */
    public static String getUserSalt(String loginName){
        // 盐值
        String[] salts = {"sun","moon","star","sky","cloud","fog","rain","wind","rainbow"};
        int hashCode = loginName.hashCode() + 159;
        int mod = Math.abs( hashCode % 9 );
        return salts[mod];
    }

    /**
     * 获取用户加密密码
     * @param loginName
     * @param password
     * @return
     */
    public static String getUserEncryptPassword(String loginName, String password){
        String pwdAndSalt = password + getUserSalt(loginName);
        return MD5Util.MD5Encode(pwdAndSalt,"utf8");
    }

    public static void main(String[] args){
        //getSaltValue("1");
        String admin = getUserEncryptPassword("admin", "111");
        System.out.println(admin);

    }
}
