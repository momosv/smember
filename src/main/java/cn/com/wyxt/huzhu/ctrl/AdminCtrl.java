package cn.com.wyxt.huzhu.ctrl;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.entity.Tips;
import cn.com.wyxt.base.mybatis.model.BasicExample;
import cn.com.wyxt.base.tokenManager.impl.AuthManager;
import cn.com.wyxt.base.util.MD5Util;
import cn.com.wyxt.base.util.UUIDRandomUtil;
import cn.com.wyxt.huzhu.model.TbAdmin;
import cn.com.wyxt.huzhu.model.TbCompany;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import cn.com.wyxt.huzhu.service.IAccountService;
import cn.com.wyxt.huzhu.service.IAdminService;
import cn.com.wyxt.huzhu.service.ICompanyService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Api(value = "admin",tags = "管理员",description = "后台登录")
@RequestMapping("admin")
@RestController
public class AdminCtrl {

    private TbAdminVO getUser(){
      return   AuthManager.getAdminVO();
    }

    @Autowired
    IAccountService accountService;
    @Autowired
    ICompanyService companyService;
    @Autowired
    IAdminService adminService;

    @ApiOperation(value = "企业列表",notes = "列表",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyName",value="企业名字",dataType="string", paramType = "query",example="xxx"),
            @ApiImplicitParam(name="pageNum",value="页码",dataType="string", paramType = "query",example="如题",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="页面大小（条数）",dataType="string", paramType = "query",defaultValue = "10"),
    })
    @RequestMapping("getCompanyList")
    public Msg getCompanyList(@RequestParam(required = false,defaultValue = "") String companyName,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
                getUser();
               BasicExample ex = new BasicExample(TbCompany.class);
               Page page = PageHelper.startPage(pageNum, pageSize);
               if(!StringUtils.isEmpty(companyName)) {
                   ex.createCriteria().andVarFullLike("name", companyName);
                }
                ex.setOrderByClause("create_time desc");
                companyService.selectByExample(ex);
                PageInfo info=new PageInfo(page.getResult());

                return Msg.success().add("page",info);
    }

    @ApiOperation(value = "新增企业",notes = "新增企业",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("addCompany")
    public Msg addCompany(TbCompany company) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        if(StringUtils.isEmpty(company.getName())){
            return Msg.fail(Tips.COMP_NAME_NULL);
        }
        if(StringUtils.isEmpty(company.getSocialCreditCode())){
            return Msg.fail(Tips.COMP_CODE_NULL);
        }
        if(StringUtils.isEmpty(company.getEmail())){
            return Msg.fail(Tips.COMP_EMAIL_NULL);
        }
       if(accountService.validCompAccountExist(company.getEmail(), company.getEmail())){
           return Msg.fail("邮箱账号已存在");
       }
       company.setId(UUIDRandomUtil.get32UUID());
        companyService.addCompany(company);
        return Msg.success();
    }

    @ApiOperation(value = "新增企业",notes = "新增企业",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("updateCompany")
    public Msg updateCompany(TbCompany company) throws Exception {
        TbAdminVO adminVO=getUser();
        if(StringUtils.isEmpty(company.getName())){
            return Msg.fail(Tips.COMP_NAME_NULL);
        }
        if(StringUtils.isEmpty(company.getSocialCreditCode())){
            return Msg.fail(Tips.COMP_CODE_NULL);
        }
        if(StringUtils.isEmpty(company.getEmail())){
            return Msg.fail(Tips.COMP_EMAIL_NULL);
        }

        TbCompany tbCompany = companyService.selectByPrimaryKey(TbCompany.class,company.getId());
        if(null == tbCompany) return Msg.fail("数据不存在或者被删除");
        companyService.updateCompany(company);
        return Msg.success();
    }

    @ApiOperation(value = "获取企业详情",notes = "获取企业详情",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("companyDetail")
    public Msg companyDetail(String id) throws Exception {
        TbCompany tbCompany = companyService.selectByPrimaryKey(TbCompany.class,id);
        if(null == tbCompany) return Msg.fail().add("detail",tbCompany);
        return Msg.success().add("detail",tbCompany);
    }



    @ApiOperation(value = "账号列表",notes = "账号列表",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="账号名字",dataType="string", paramType = "query",example="xxx"),
            @ApiImplicitParam(name="pageNum",value="页码",dataType="string", paramType = "query",example="如题",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="页面大小（条数）",dataType="string", paramType = "query",defaultValue = "10"),
    })
    @RequestMapping("getAAccountList")
    public Msg getAAccountList(@RequestParam(required = false,defaultValue = "") String name,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        TbAdminVO admin =  getUser();
        Page page = PageHelper.startPage(pageNum, pageSize);
        accountService.getAccountList(name,admin);
        PageInfo info=new PageInfo(page.getResult());
        return Msg.success().add("page",info);
    }


    @ApiOperation(value = "新增子账号",notes = "新增子账号",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("addAAccount")
    public Msg addAAccount(TbAdmin admin) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        TbAdminVO adminVO =getUser();
        if(StringUtils.isEmpty(admin.getName())){
            return Msg.fail("账号名称不能为空");
        }
        if(StringUtils.isEmpty(admin.getAccount())){
            return Msg.fail("账号不能为空");
        }
        if(StringUtils.isEmpty(admin.getPsw())){
            return Msg.fail("密码不能为空");
        }
        if(admin.getPsw().length()<6){
            return Msg.fail("密码长度不能少于6位");
        }
        if(accountService.validAdminAccountExist(admin.getAccount(), "")){
            return Msg.fail("账号已存在");
        }
        admin.setId(UUIDRandomUtil.get32UUID());
        admin.setCreateTime(new Date());
        admin.setGrade(adminVO.getGrade()+1);
        admin.setParentId(adminVO.getId());
        admin.setPsw(MD5Util.encrypt(admin.getPsw()));
        admin.setType(adminVO.getType());
        accountService.insertOne(admin);
        return Msg.success();
    }

    @ApiOperation(value = "获账号详情",notes = "获子账号详情",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("aAccountDetail")
    public Msg aAccountDetail(String id) throws Exception {
        TbAdminVO admin = accountService.getAccount(id);
        if(null == admin) return Msg.fail();
        return Msg.success().add("detail",admin);
    }

    @ApiOperation(value = "修改账号",notes = "修改账号",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("updateAAccount")
    public Msg updateAAccount(TbAdmin admin) throws Exception {
        TbAdminVO adminVO=getUser();
        if(StringUtils.isEmpty(admin.getName())){
            return Msg.fail("账号名称不能为空");
        }
        if(StringUtils.isEmpty(admin.getAccount())){
            return Msg.fail("账号不能为空");
        }
        if(StringUtils.isEmpty(admin.getPsw())){
            return Msg.fail("密码不能为空");
        }
        if(admin.getPsw().length()<6){
            return Msg.fail("密码长度不能少于6位");
        }
        if(accountService.validAdminAccountExist(admin.getAccount(), admin.getId())){
            return Msg.fail("账号已存在");
        }
        admin.setPsw(MD5Util.encrypt(admin.getPsw()));

        TbAdmin admin0 = accountService.selectByPrimaryKey(TbAdmin.class,admin.getId());
        if(null == admin0) return Msg.fail("数据不存在或者被删除");
        if(adminVO.getGrade()!=0&&adminVO.getId().equals(admin0.getParentId())){
            return Msg.fail("您不是该账号的创建者");
        }
        accountService.updateOne(admin0,true);
        return Msg.success();
    }

    @ApiOperation(value = "删除账号",notes = "删除账号",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("deleteAAccount")
    public Msg deleteAAccount(String id) throws Exception {
        TbAdminVO adminVO=getUser();
        TbAdmin admin0 = accountService.selectByPrimaryKey(TbAdmin.class,id);
        if(null == admin0) return Msg.fail("数据不存在或者被删除");
        if(adminVO.getGrade()!=0&&adminVO.getId().equals(admin0.getParentId())){
            return Msg.fail("您不是该账号的创建者");
        }
        accountService.deleteByPrimaryKey(TbAdmin.class,id);
        return Msg.success();
    }
    @ApiOperation(value = "账户金额增加",notes = "账户金额增加",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("updateCompanyAmount")
    public Msg updateCompanyAmount(String id,Integer amount) throws Exception {
        TbAdminVO adminVO=getUser();
        if(adminVO.getGrade()!=0&&(adminVO.getAuthority()==null||!adminVO.getAuthority().contains("updateCompanyAmount"))){
            return Msg.fail("您无权限更新账户金额！");
        }
        if(amount==null||amount<=0){
            return Msg.fail("金额不能小于或等于0");
        }
        accountService.updateCompanyAmount(id,amount);
        return Msg.success();
    }

    @ApiOperation(value = "更新密码",notes = "更新密码",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("changePsw")
    public Msg changePsw(String psw,String oldPsw) throws Exception {
        TbAdminVO adminVO=getUser();
        if(psw==null||psw.length()<6){
            return Msg.fail("密码不能少于六位");
        }
        TbAdmin admin = accountService.selectByPrimaryKey(TbAdmin.class,adminVO.getId());
        if (!admin.getPsw().equals(MD5Util.encrypt(oldPsw))){
            return Msg.fail("原密码错误");
        }
        admin.setPsw(MD5Util.encrypt(psw));

        accountService.updateOne(admin,true);
        return Msg.success();
    }
    @ApiOperation(value = "getRuleList",notes = "getRuleList",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("getRuleList")
    public Msg getRuleList() throws Exception {
        TbAdminVO adminVO=getUser();
        return Msg.success().add("rule",adminService.getRule());
    }

    @ApiOperation(value = "getRuleList",notes = "getRuleList",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("saveRule")
    public Msg saveRule(String rule) throws Exception {
        adminService.saveRule(rule);
       return Msg.success();
    }


}
