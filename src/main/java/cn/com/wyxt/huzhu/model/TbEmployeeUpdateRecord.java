package cn.com.wyxt.huzhu.model;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.util.Date;

public class TbEmployeeUpdateRecord extends IBaseDBPO{

    public  String _getTableName(){
        return "tb_employee_update_record";
    }

    public String _getPKColumnName(){
        return "id";
    };

    public Object _getPKValue(){
        return id;
    };

    public void _setPKValue(Object var){
        this.id=Integer.valueOf(var.toString());
    };
    private Integer id;

    private String employeeId;

    private Integer oldCategory;

    private Integer newCategory;

    private String creatorId;

    private String creatorName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Integer getOldCategory() {
        return oldCategory;
    }

    public void setOldCategory(Integer oldCategory) {
        this.oldCategory = oldCategory;
    }

    public Integer getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(Integer newCategory) {
        this.newCategory = newCategory;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}