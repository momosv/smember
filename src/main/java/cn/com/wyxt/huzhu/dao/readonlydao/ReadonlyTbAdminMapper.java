package cn.com.wyxt.huzhu.dao.readonlydao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbAdmin;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReadonlyTbAdminMapper  extends BasicMapper {
    TbAdmin selectByPrimaryKey(String id);

    @Select("select account,id,type,name,parent_id,grade,create_time from tb_admin where account=#{account} and psw = #{psw}")
    TbAdminVO selectByAccount(@Param("account") String account, @Param("psw") String psw);

    final static String SQL_GET_ADMIN_ACCOUNT= "select s.account,s.id,s.type,s.name,s.parent_id,s.grade,s.create_time,m.name as parent_name" +
            " from tb_admin s left join tb_admin m on m.id=s.parent_id" ;

    @Select("<script>" +
            SQL_GET_ADMIN_ACCOUNT +
            " where  s.type=#{type}  and  s.grade &gt;   #{grade} " +
            "<when test='ids!=null'>" +
            " and s.id in " +
             "<foreach item='item' index='index' collection='ids'      open='(' separator=',' close=')'>"+
             "#{item}"+
             "</foreach>"+
            " </when> " +
            "and s.name like #{name} " +
            "<when test='parentId!=null'>" +
            " and s.parent_id = #{parentId} " +
            " </when> " +
            "<when test='name!=null'>" +
            "and s.name like #{name} " +
            " </when> " +
            " order by create_time desc" +
            "</script>")
    List<TbAdminVO> getAccountList(@Param("ids") String[] ids,@Param("name") String name, @Param("grade") Integer grade, @Param("parentId") String parentId,@Param("type")Integer type);

    @Select("<script>" +
             SQL_GET_ADMIN_ACCOUNT +
            " where"+
            "  s.id = #{id} " +
            " order by create_time desc" +
            "</script>")
    TbAdminVO getAccount(@Param("id") String id);
}