package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.Serializable;
import java.sql.Timestamp;

public class Images implements Serializable {
    private static final long serialVersionUID = 2946547722666686212L;
    private Long id;
    private String name;
    private String path;
    private Long size;
    private Byte type;
    private Timestamp version;
    private String operator;

    public Images(Long id) {
        this.id = id;
    }

    public Images() {
    }

    public Images(String name, String path, Byte type) {
        this.name = name;
        this.path = path;
        this.type = type;
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

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
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

