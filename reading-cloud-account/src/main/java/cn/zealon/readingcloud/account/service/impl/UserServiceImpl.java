package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.bo.UserBO;
import cn.zealon.readingcloud.account.common.utils.JwtUtil;
import cn.zealon.readingcloud.account.common.utils.UserUtil;
import cn.zealon.readingcloud.account.dao.UserMapper;
import cn.zealon.readingcloud.account.service.UserService;
import cn.zealon.readingcloud.account.vo.AuthVO;
import cn.zealon.readingcloud.account.vo.UserVO;
import cn.zealon.readingcloud.common.pojo.account.User;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.common.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import static cn.zealon.readingcloud.common.constant.JwtConstant.EXPIRE_DAY;

/**
 * 账户服务
 * @author: zealon
 * @since: 2020/4/11
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result register(UserBO userBO) {
        User dbUser = this.userMapper.selectByLoginName(userBO.getLoginName());
        if (dbUser != null) {
            return ResultUtil.verificationFailed().buildMessage(userBO.getLoginName() + "已存在，请使用其它登录名进行注册！");
        }

        User user = new User();
        BeanUtils.copyProperties(userBO, user);
        String encryptPwd = UserUtil.getUserEncryptPassword(userBO.getLoginName(), userBO.getUserPwd());
        user.setUserPwd(encryptPwd);
        try{
            // 设置默认头像
            user.setHeadImgUrl("http://reading.zealon.cn/default.jpg");
            user.setUuid(CommonUtil.getUUID());
            this.userMapper.insert(user);
        } catch (Exception ex) {
            LOGGER.error("注册用户失败了！{}; user:{}", ex, user);
            return ResultUtil.fail().buildMessage("注册失败，服务器被吃了! ＝(#>д<)ﾉ");
        }
        return ResultUtil.success().buildMessage("注册成功！请登录吧");
    }

    @Override
    public Result<AuthVO> login(String loginName, String password) {
        try{
            User user = this.userMapper.selectByLoginName(loginName);
            if (null == user) {
                return ResultUtil.notFound().buildMessage("登录失败，用户不存在！");
            }

            // 校验用户密码
            String encryptPwd = UserUtil.getUserEncryptPassword(loginName, password);
            if (!user.getUserPwd().equals(encryptPwd)) {
                return ResultUtil.verificationFailed().buildMessage("登录失败，密码输入错误！");
            }

            // 登录成功，返回用户信息
            AuthVO vo = new AuthVO();
            UserVO userVo = new UserVO();
            BeanUtils.copyProperties(user, userVo);
            vo.setToken(JwtUtil.buildJwt(this.getLoginExpre(), userVo));
            vo.setUser(userVo);
            return ResultUtil.success(vo);
        } catch (Exception ex) {
            LOGGER.error("登录失败了！{}; loginName:{}", ex, loginName);
            return ResultUtil.fail().buildMessage("登录失败，服务器被吃了＝(#>д<)ﾉ ！请重试。 ");
        }
    }

    @Override
    public Result logout(String loginName) {
        return null;
    }

    @Override
    public Result update(UserBO userBO) {
        return null;
    }

    @Override
    public Result updatePassword(UserBO userBO) {
        return null;
    }

    /**
     * 获取登陆过期时间
     * @return
     */
    private Date getLoginExpre(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_DAY);
        return calendar.getTime();
    }
}