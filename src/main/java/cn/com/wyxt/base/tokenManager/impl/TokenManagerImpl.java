package cn.com.wyxt.base.tokenManager.impl;


import cn.com.wyxt.base.entity.UserInfoPO;
import cn.com.wyxt.base.redis.util.RedisUtils;
import cn.com.wyxt.base.tokenManager.TokenManager;
import cn.com.wyxt.base.util.Constants;
import cn.com.wyxt.base.util.SpringUtil;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManagerImpl implements TokenManager {

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    public String getToken(UserInfoPO userInfo){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        RedisUtils.set(token,userInfo,30,TimeUnit.MINUTES);
        return token;
    }

    /**
     * 刷新用户
     * @param token
     */
    public void refreshUserToken(String token){
        if(RedisUtils.hasKey(token)){
            RedisUtils.expire(token,60*60);
        }
    }

    /**
     * 用户退出登陆
     * @param token
     */
    public void loginOff(String token){
        RedisUtils.delete(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public UserInfoPO getUserInfoByToken(String token){
        if(RedisUtils.hasKey(token)){
            return (UserInfoPO)RedisUtils.get(token);
        }
        return null;
    }


}
