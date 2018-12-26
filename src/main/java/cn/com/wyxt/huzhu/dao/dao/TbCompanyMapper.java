package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCompanyMapper  extends BasicMapper {
    TbCompany selectByPrimaryKey(String id);
}