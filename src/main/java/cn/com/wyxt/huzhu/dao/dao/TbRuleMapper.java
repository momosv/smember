package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import cn.com.wyxt.huzhu.model.TbRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TbRuleMapper extends BasicMapper{
    TbRule selectByPrimaryKey(Integer id);


    @Select("select * from tb_rule where STATUS = 1 ORDER BY type,category,left_age")
    List<TbRule> getRule();
}