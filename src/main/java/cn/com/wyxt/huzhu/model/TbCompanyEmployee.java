package cn.com.wyxt.huzhu.model;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.util.Date;

public class TbCompanyEmployee extends IBaseDBPO{


    public  String _getTableName(){
        return "tb_company_employee";
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

    private String companyId;

    private String name;

    private Byte auth;

    private String phone;

    private String email;

    private String address;

    private String idCardNum;

    private String idCardImgUrl;

    private Integer sex;

    private Date birthday;

    private Integer age;

    private Integer workCategory;

    private Date createTime;

    private Date updateTime;

    private Date authTime;

    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getAuth() {
        return auth;
    }

    public void setAuth(Byte auth) {
        this.auth = auth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum == null ? null : idCardNum.trim();
    }

    public String getIdCardImgUrl() {
        return idCardImgUrl;
    }

    public void setIdCardImgUrl(String idCardImgUrl) {
        this.idCardImgUrl = idCardImgUrl == null ? null : idCardImgUrl.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(Integer workCategory) {
        this.workCategory = workCategory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}