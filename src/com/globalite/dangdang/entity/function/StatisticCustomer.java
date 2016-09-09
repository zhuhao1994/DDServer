package com.globalite.dangdang.entity.function;

/**
 * Created by zhu on 2016/9/5.
 */
import java.math.BigDecimal;

public class StatisticCustomer {
    private Byte sex = null;
    private Integer count = null;
    private BigDecimal maleRate = null;
    private BigDecimal femaleRate = null;

    public StatisticCustomer() {
    }

    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getMaleRate() {
        return this.maleRate;
    }

    public void setMaleRate(BigDecimal maleRate) {
        this.maleRate = maleRate;
    }

    public BigDecimal getFemaleRate() {
        return this.femaleRate;
    }

    public void setFemaleRate(BigDecimal femaleRate) {
        this.femaleRate = femaleRate;
    }
}

