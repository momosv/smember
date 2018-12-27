package cn.com.wyxt.huzhu.ctrl;

import cn.com.wyxt.base.entity.Msg;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "admin",tags = "管理员",description = "后台登录")
@RequestMapping("admin")
@RestController
public class AdminCtrl {


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
}
