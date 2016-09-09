package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.Serializable;
import java.sql.Timestamp;

public class City implements Serializable {
    private static final long serialVersionUID = 7906025936629221L;
    private String id;
    private String name;
    private Byte type;
    private String parentid;
    private Timestamp version;
    private String operator;

    public City() {
    }

    public City(String id) {
        this.id = id;
    }

    public City(String name, Byte type, String parentid) {
        this.name = name;
        this.type = type;
        this.parentid = parentid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
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
