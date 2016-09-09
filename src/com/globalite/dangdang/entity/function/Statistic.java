package com.globalite.dangdang.entity.function;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.Serializable;
import java.util.Date;

public class Statistic implements Serializable {
    private static final long serialVersionUID = 5535964321351800643L;
    private Date cd = null;
    private Integer cnt = null;
    private String name = null;

    public Statistic() {
    }

    public Date getCd() {
        return this.cd;
    }

    public void setCd(Date cd) {
        this.cd = cd;
    }

    public Integer getCnt() {
        return this.cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
