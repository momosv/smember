package cn.com.wyxt.huzhu.service;

import cn.com.wyxt.base.mybatis.service.BasicService;


public interface IAdminService extends BasicService{

    Object  getRule();

    void saveRule(String rule, String future) throws IllegalAccessException, InstantiationException, CloneNotSupportedException;

    Object getFutureRule();
}
