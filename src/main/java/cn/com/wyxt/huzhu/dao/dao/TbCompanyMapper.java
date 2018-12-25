package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.huzhu.model.TbCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCompanyMapper {
    TbCompany selectByPrimaryKey(String id);
}