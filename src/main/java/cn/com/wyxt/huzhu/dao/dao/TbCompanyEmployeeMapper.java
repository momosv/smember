package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.huzhu.model.TbCompanyEmployee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCompanyEmployeeMapper {
    TbCompanyEmployee selectByPrimaryKey(String id);
}