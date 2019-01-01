package cn.com.wyxt.huzhu.service;

import cn.com.wyxt.base.mybatis.service.BasicService;
import cn.com.wyxt.huzhu.model.TbCompany;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/28 14:28
 **/
@Transactional
public interface ICompanyService extends BasicService {
    void addCompany(TbCompany company) throws UnsupportedEncodingException;

    void updateCompany(TbCompany company);
}
