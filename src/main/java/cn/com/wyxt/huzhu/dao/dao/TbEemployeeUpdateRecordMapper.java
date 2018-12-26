package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbEmployeeUpdateRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbEemployeeUpdateRecordMapper  extends BasicMapper {
    TbEmployeeUpdateRecord selectByPrimaryKey(Integer id);
}