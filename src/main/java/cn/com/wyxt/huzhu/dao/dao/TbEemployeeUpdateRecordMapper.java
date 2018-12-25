package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.huzhu.model.TbEmployeeUpdateRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbEemployeeUpdateRecordMapper {
    TbEmployeeUpdateRecord selectByPrimaryKey(Integer id);
}