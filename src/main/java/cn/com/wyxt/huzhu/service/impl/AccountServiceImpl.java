package cn.com.wyxt.huzhu.service.impl;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.mybatis.model.BasicExample;
import cn.com.wyxt.base.mybatis.service.impl.BasicServiceImpl;
import cn.com.wyxt.base.tokenManager.impl.AuthManager;
import cn.com.wyxt.base.util.MD5Util;
import cn.com.wyxt.base.util.SpringUtil;
import cn.com.wyxt.huzhu.dao.dao.TbCompanyAccountMapper;
import cn.com.wyxt.huzhu.dao.readonlydao.ReadonlyTbAdminMapper;
import cn.com.wyxt.huzhu.model.TbAdmin;
import cn.com.wyxt.huzhu.model.TbCompanyAccount;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import cn.com.wyxt.huzhu.modelVO.TbCompanyAccountVO;
import cn.com.wyxt.huzhu.service.IAccountService;
import cn.com.wyxt.huzhu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("accountService")
public class AccountServiceImpl extends BasicServiceImpl implements IAccountService {

    @Autowired
    TbCompanyAccountMapper tbCompanyAccountMapper;
    @Autowired
    ReadonlyTbAdminMapper readonlyTbAdminMapper;

    @Autowired
    IAdminService adminService;

    @Autowired
    public void setMapper(){
        setMapper(tbCompanyAccountMapper);
    }

    //110 系统管理员  1普通管理员
    @Override
    public Msg validLogin(String account, String psw, Integer type) throws Exception {
        String psw5 = MD5Util.encrypt(psw);
       if(type==1){//管理员
          TbAdminVO adminVO = readonlyTbAdminMapper.selectByAccount(account,psw5);
          if(adminVO==null){
            return Msg.fail("登录失败,账号或密码有误");
          }
           AuthManager.loginOn(adminVO);
           SpringUtil.getHttpServletResponse().setHeader("redirect","admin");
           SpringUtil.getHttpServletResponse().setHeader("type","adminVO.getType()");
          return Msg.success().add("user",adminVO).add("type",adminVO.getType()).add("redirect","admin");
       }else{//企业
           TbCompanyAccountVO tbCompanyAccountVO = tbCompanyAccountMapper.selectByAccount(account,psw5);
           if(tbCompanyAccountVO==null){
               return Msg.fail("登录失败,账号或密码有误");
           }
           AuthManager.loginOn(tbCompanyAccountVO);
           SpringUtil.getHttpServletResponse().setHeader("redirect","company");
           return Msg.success().add("user",tbCompanyAccountVO).add("redirect","company");
       }
    }

    @Override
    public boolean validCompAccountExist(String email, String extId) throws IllegalAccessException, InstantiationException {
        BasicExample ex = new BasicExample(TbCompanyAccount.class);
        ex.createCriteria().andVarEqualTo("account", email).andVarNotEqualTo("id",extId);
       int count = this.countByExample(ex);
        return count>0;
    }

    @Override
    public List getAccountList(String name, TbAdminVO admin) throws Exception {
        if(admin.getGrade()==0) {
            return readonlyTbAdminMapper.getAccountList(null,"%"+name+"%",admin.getGrade(),null,admin.getType());
        }
        return  readonlyTbAdminMapper.getAccountList(null,"%"+name+"%",admin.getGrade(),admin.getId(),admin.getType());

    }
    @Override
    public TbAdminVO getAccount(String id) throws Exception {
            return readonlyTbAdminMapper.getAccount(id);

    }

    @Override
    public List getSuperChildAccountList(String name, TbAdminVO admin) throws Exception {
        BasicExample ex = new BasicExample(TbAdmin.class);
        BasicExample.Criteria criteria = ex.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andVarFullLike("name", name);
        }
        criteria.andVarEqualTo("type",admin.getType().toString());
        criteria.andVarNotEqualTo("id",admin.getId());
        ex.setOrderByClause("create_time desc");
        return this.selectByExample(ex);
    }

    @Override
    public boolean validAdminAccountExist(String account, String ext) throws IllegalAccessException, InstantiationException {
        BasicExample ex = new BasicExample(TbAdmin.class);
        ex.createCriteria().andVarEqualTo("account", account).andVarNotEqualTo("id",ext);
        int count = this.countByExample(ex);
        return count>0;
    }


    @Override
    public void updateCompanyAmount(String id, Integer amount) throws Exception {
        tbCompanyAccountMapper.updateCompanyAmount(id,amount);
    }



}
