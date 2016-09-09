package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Customerlevel implements Serializable {
    private static final long serialVersionUID = -7050507004584963791L;
    private Long id;
    private String name;
    private String content;
    private Long point;
    private BigDecimal discount;
    private String remark;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;

    public Customerlevel() {
    }

    public Customerlevel(Long id) {
        this.id = id;
    }

    public Customerlevel(String name, Long point, BigDecimal discount, Byte deleteflag) {
        this.name = name;
        this.point = point;
        this.discount = discount;
        this.deleteflag = deleteflag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPoint() {
        return this.point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Byte deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Timestamp getVersion() {
        return this.version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
