package cn.com.wyxt.huzhu.service;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.exception.DiyException;
import cn.com.wyxt.base.mybatis.service.BasicService;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAccountService extends BasicService {

    public Msg validLogin(String account, String psw , Integer type) throws UnsupportedEncodingException, Exception;

    boolean validCompAccountExist(String email, String extId) throws IllegalAccessException, InstantiationException;

    List getAccountList(String name, TbAdminVO admin) throws Exception;

    TbAdminVO getAccount(String id) throws Exception;

    List getSuperChildAccountList(String name, TbAdminVO admin) throws Exception;

    boolean validAdminAccountExist(String account, String ext) throws IllegalAccessException, InstantiationException;

    void updateCompanyAmount(String id, Integer amount) throws Exception;
}

