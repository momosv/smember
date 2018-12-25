package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.huzhu.model.TbEmployeeExpenses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbEmployeeExpensesMapper {
    TbEmployeeExpenses selectByPrimaryKey(String id);
}