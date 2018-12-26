package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbEmployeeExpenses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbEmployeeExpensesMapper  extends BasicMapper {
    TbEmployeeExpenses selectByPrimaryKey(String id);
}