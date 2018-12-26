package cn.com.wyxt.huzhu.dao.dao;

import cn.com.wyxt.base.entity.TbExceptionLog;
import cn.com.wyxt.base.mybatis.dao.BasicMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TbExceptionLogMapper extends BasicMapper {
    TbExceptionLog selectByPrimaryKey(Integer id);
}