package cn.com.wyxt.huzhu.ctrl;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.entity.Tips;
import cn.com.wyxt.base.interfaces.AuthIgnore;
import cn.com.wyxt.base.util.MD5Util;
import cn.com.wyxt.huzhu.service.IAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Api(value = "LoginCtrl",tags = "登录",description = "后台登录")
@AuthIgnore
@RequestMapping("/login")
@RestController
public class LoginCtrl {

    @Autowired
    IAccountService accountService;

    @ApiOperation(value = "企业用户提交反馈单",notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userType",value="类型，0 企业，1管理员",dataType="string", paramType = "query",example="0",required = true),
            @ApiImplicitParam(name="account",value="账号",dataType="string", paramType = "query",example="admin",required = true),
            @ApiImplicitParam(name="psw",value="密码",dataType="string", paramType = "query",example="123",required = true),
    })
    @RequestMapping("valid")
    public Msg valid(@RequestParam(defaultValue = "0") Integer userType,String account, String psw) throws Exception {

        if(StringUtils.isEmpty(account)||StringUtils.isEmpty(psw)){
            return Msg.fail(Tips.ACCOUNT_PSW_NULL);
        }
        accountService.validLogin(account,psw,userType);
        return Msg.success();
    }
}
