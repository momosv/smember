package cn.com.wyxt.huzhu.modelVO;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.util.Date;
import java.util.Set;

public class TbAdminVO extends IBaseDBPO{

    private static final long serialVersionUID = 1L;

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

    private Date createTime;

    private String parentId;
    private String parentName;

    private Integer grade;

    private Integer type;

    private Set<String> authority;

    public Set<String> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<String> authority) {
        this.authority = authority;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

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