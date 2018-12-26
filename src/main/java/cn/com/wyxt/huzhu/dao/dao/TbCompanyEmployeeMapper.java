package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbCompanyEmployee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCompanyEmployeeMapper  extends BasicMapper {
    TbCompanyEmployee selectByPrimaryKey(String id);
}