package cn.com.wyxt.huzhu.service;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.mybatis.service.BasicService;

import java.io.UnsupportedEncodingException;

public interface IAccountService extends BasicService {

    public Msg validLogin(String account, String psw , Integer type) throws UnsupportedEncodingException, Exception;
}
