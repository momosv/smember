package cn.com.wyxt.base.tokenManager.impl;


import cn.com.wyxt.base.tokenManager.TokenManager;
import cn.com.wyxt.base.util.Constants;
import cn.com.wyxt.base.util.SpringUtil;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import cn.com.wyxt.huzhu.modelVO.TbCompanyAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component("authManager")
public class AuthManager {

    @Autowired
    private TokenManager tokenManager;

    /**
     * 获取请求体
     * @return
     */
    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 注销该访问用户
     */
    public static TbAdminVO getAdminVO(){
         Object obj = SpringUtil.getHttpServletRequest().getSession().getAttribute(Constants.LOGIN_ADMIN);
         if(obj==null){
             return null;
         }
         return (TbAdminVO) obj;
    }
    /**
     * 注销该访问用户
     */
    public static TbCompanyAccountVO getCompVO(){
        Object obj = SpringUtil.getHttpServletRequest().getSession().getAttribute(Constants.LOGIN_COMPANY);
        if(obj==null){
            return null;
        }
        return (TbCompanyAccountVO) obj;
    }
    /**
     * 注销该访问用户
     */
    public static void loginAdminOff(){
        SpringUtil.getHttpServletRequest().getSession().removeAttribute(Constants.LOGIN_ADMIN);
    }
    /**
     * 注销该访问用户
     */
    public static void loginCompOff(){
        SpringUtil.getHttpServletRequest().getSession().removeAttribute(Constants.LOGIN_COMPANY);
    }
    /**
     * 登录
     * @return
     */
    public static void loginOn(TbAdminVO adminVO){
        SpringUtil.getHttpServletRequest().getSession().setAttribute(Constants.LOGIN_ADMIN,adminVO);
    }
    /**
     * 登录
     * @return
     */
    public static void loginOn(TbCompanyAccountVO companyAccountVO){
        SpringUtil.getHttpServletRequest().getSession().setAttribute(Constants.LOGIN_COMPANY,companyAccountVO);
    }
}
