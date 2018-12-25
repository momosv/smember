package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbCompanyAccount;
import cn.com.wyxt.huzhu.modelVO.TbCompanyAccountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TbCompanyAccountMapper extends BasicMapper{
    TbCompanyAccount selectByPrimaryKey(String id);

    @Select("select a.account,a.id,a.name,a.parent_id,a.grade,create_time,t.* from tb_company_account a,tb_company t " +
            " where a.company_id=t.id and  account=#{account} and psw = #{psw}")
    TbCompanyAccountVO selectByAccount(@Param("account") String account, @Param("psw") String psw);
}