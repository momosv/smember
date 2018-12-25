package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.huzhu.model.TbAdmin;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TbAdminMapper {
    TbAdmin selectByPrimaryKey(String id);

    @Select("select account,id,type,name,parent,grade,create_time from tb_admin where account=#{account} and psw = #{psw}")
    TbAdminVO selectByAccount(@Param("account") String account, @Param("psw")String psw);
}