package cn.com.wyxt.huzhu.model;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.util.Date;

public class TbAdmin extends IBaseDBPO{


    public  String _getTableName(){
        return "tb_admin";
    }

    public String _getPKColumnName(){
        return "id";
    };

    public Object _getPKValue(){
        return id;
    };

    public void _setPKValue(Object var){
        this.id=var.toString();
    };

    private String id;

    private String name;

    private String account;

    private String psw;

    private Date createTime;

    private String parentId;

    private Integer grade;

    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw == null ? null : psw.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}