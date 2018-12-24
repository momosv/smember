package cn.com.wyxt.base.exception;


import cn.com.wyxt.base.entity.TbExceptionLog;
import cn.com.wyxt.base.mybatis.service.BasicService;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/13 11:05
 **/

public interface IExceptionLogService extends BasicService {
    void insertExceptionLogSelective(TbExceptionLog exceptionLog);
}
