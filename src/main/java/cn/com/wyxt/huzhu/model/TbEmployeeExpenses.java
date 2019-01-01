package cn.com.wyxt.huzhu.model;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.math.BigDecimal;
import java.util.Date;

public class TbEmployeeExpenses extends IBaseDBPO{


    public  String _getTableName(){
        return "tb_employee_expenses";
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

    private Date createTime;

    private BigDecimal cost;

    private String employeeId;

    private Integer preWorkCategory;

    private BigDecimal factor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Integer getPreWorkCategory() {
        return preWorkCategory;
    }

    public void setPreWorkCategory(Integer preWorkCategory) {
        this.preWorkCategory = preWorkCategory;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }
}