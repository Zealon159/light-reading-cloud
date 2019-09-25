package cn.zealon.user.entity;

/**
 * @Author: tangyl
 * @Date: 2019/3/4
 * @Version: 1.0
 */
public class User {
    private Long uid;
    private String userName;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
