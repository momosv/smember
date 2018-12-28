package cn.com.wyxt.huzhu.ctrl;

import cn.com.wyxt.base.entity.Msg;
import cn.com.wyxt.base.entity.Tips;
import cn.com.wyxt.base.util.UUIDRandomUtil;
import cn.com.wyxt.huzhu.model.TbCompany;
import cn.com.wyxt.huzhu.service.IAccountService;
import cn.com.wyxt.huzhu.service.ICompanyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Api(value = "admin",tags = "管理员",description = "后台登录")
@RequestMapping("admin")
@RestController
public class AdminCtrl {


    @Autowired
    IAccountService accountService;
    @Autowired
    ICompanyService companyService;

    @ApiOperation(value = "企业列表",notes = "列表",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyName",value="企业名字",dataType="string", paramType = "query",example="xxx"),
            @ApiImplicitParam(name="pageNum",value="页码",dataType="string", paramType = "query",example="如题",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="页面大小（条数）",dataType="string", paramType = "query",defaultValue = "10"),
    })
    @RequestMapping("getCompanyList")
    public Msg getCompanyList(@RequestParam(required = false,defaultValue = "") String companyName,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize
        ){


                return Msg.success();
    }

    @ApiOperation(value = "新增企业",notes = "新增企业",httpMethod = "POST")
    @ApiImplicitParams({})
    @RequestMapping("addCompany")
    public Msg getCompanyList(TbCompany company) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        if(StringUtils.isEmpty(company.getName())){
            return Msg.fail(Tips.COMP_NAME_NULL);
        }
        if(StringUtils.isEmpty(company.getSocialCreditCode())){
            return Msg.fail(Tips.COMP_CODE_NULL);
        }
        if(StringUtils.isEmpty(company.getEmail())){
            return Msg.fail(Tips.COMP_EMAIL_NULL);
        }
       if(accountService.validCompAccountExist(company.getEmail())){
           return Msg.fail("邮箱账号已存在");
       }
       company.setId(UUIDRandomUtil.get32UUID());
        companyService.addCompany(company);
        return Msg.success();
    }
}
