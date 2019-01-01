package cn.com.wyxt.huzhu.model;

import cn.com.wyxt.base.mybatis.model.IBaseDBPO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbRule extends IBaseDBPO  implements Cloneable,Serializable{
    public  String _getTableName(){
        return "tb_rule";
    }

    public String _getPKColumnName(){
        return "id";
    }

    public Object _getPKValue(){
        return id;
    }

    public void _setPKValue(Object var){
        id=Integer.valueOf(var.toString());
    }
    private Integer id;

    private String name;

    private Integer type;

    private BigDecimal ratio;

    private Short leftAge;

    private Short rightAge;
    private Short grp;
    private Short category;//工种才会有
    private Date createTime;
    private Date activeTime;
    private String creator;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Short getGrp() {
        return grp;
    }

    public void setGrp(Short grp) {
        this.grp = grp;
    }

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Short getLeftAge() {
        return leftAge;
    }

    public void setLeftAge(Short leftAge) {
        this.leftAge = leftAge;
    }

    public Short getRightAge() {
        return rightAge;
    }

    public void setRightAge(Short rightAge) {
        this.rightAge = rightAge;
    }

    @Override
    public TbRule clone() throws CloneNotSupportedException {
        return (TbRule)super.clone();
    }
}