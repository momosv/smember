package cn.com.wyxt.huzhu.service.impl;

import cn.com.wyxt.base.mybatis.service.impl.BasicServiceImpl;
import cn.com.wyxt.base.util.MD5Util;
import cn.com.wyxt.base.util.UUIDRandomUtil;
import cn.com.wyxt.huzhu.dao.dao.TbCompanyAccountMapper;
import cn.com.wyxt.huzhu.dao.dao.TbCompanyMapper;
import cn.com.wyxt.huzhu.dao.readonlydao.ReadonlyTbAdminMapper;
import cn.com.wyxt.huzhu.model.TbCompany;
import cn.com.wyxt.huzhu.model.TbCompanyAccount;
import cn.com.wyxt.huzhu.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Service("companyService")
@Transactional
public class CompanyServiceImpl extends BasicServiceImpl implements ICompanyService {

    @Autowired
    TbCompanyAccountMapper tbCompanyAccountMapper;
    @Autowired
    TbCompanyMapper tbCompanyMapper;
    @Autowired
    ReadonlyTbAdminMapper readonlyTbAdminMapper;

    @Autowired
    public void setMapper(){
        setMapper(tbCompanyMapper);
    }

    @Override
    public void addCompany(TbCompany company) throws UnsupportedEncodingException {
        TbCompanyAccount account = new TbCompanyAccount();
        account.setId(UUIDRandomUtil.get32UUID());
        account.setAccount(company.getEmail());
        account.setPsw(MD5Util.encrypt(company.getSocialCreditCode().substring(company.getSocialCreditCode().length()-6,company.getSocialCreditCode().length())));
        account.setName(company.getName());
        account.setGrade(0);
        account.setCreateTime(new Date());
        account.setCompanyId(company.getId());

        this.insertOne(company);
        this.insertOne(account);
    }

    @Override
    public void updateCompany(TbCompany company) {
        this.updateOne(company);
    }

}
