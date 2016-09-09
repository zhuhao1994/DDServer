package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Bookcomment;
import com.globalite.dangdang.entity.City;
import com.globalite.dangdang.entity.Consignmentaddress;
import com.globalite.dangdang.entity.Customerlevel;
import com.globalite.dangdang.entity.Images;
import com.globalite.dangdang.entity.Orders;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = -5435541675005527993L;
    private Long id;
    private City city;
    private Customerlevel customerlevel;
    private String regname;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private Date birthday;
    private Byte sex;
    private Short age;
    private Byte identity;
    private Images head;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;
    private List<Orders> orderses = new ArrayList();
    private List<Bookcomment> bookcomments = new ArrayList();
    private List<Consignmentaddress> consignmentaddresses = new ArrayList();

    public Customer(Long id) {
        this.id = id;
    }

    public Customer() {
    }

    public Customer(Customerlevel customerlevel, String name, String mobile, String email, String password, Byte deleteflag) {
        this.customerlevel = customerlevel;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.deleteflag = deleteflag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Customerlevel getCustomerlevel() {
        return this.customerlevel;
    }

    public void setCustomerlevel(Customerlevel customerlevel) {
        this.customerlevel = customerlevel;
    }

    public String getRegname() {
        return this.regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Short getAge() {
        return this.age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Byte getIdentity() {
        return this.identity;
    }

    public void setIdentity(Byte identity) {
        this.identity = identity;
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

    public List<Orders> getOrderses() {
        return this.orderses;
    }

    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }

    public List<Bookcomment> getBookcomments() {
        return this.bookcomments;
    }

    public void setBookcomments(List<Bookcomment> bookcomments) {
        this.bookcomments = bookcomments;
    }

    public List<Consignmentaddress> getConsignmentaddresses() {
        return this.consignmentaddresses;
    }

    public void setConsignmentaddresses(List<Consignmentaddress> consignmentaddresses) {
        this.consignmentaddresses = consignmentaddresses;
    }

    public Images getHead() {
        return this.head;
    }

    public void setHead(Images head) {
        this.head = head;
    }
}
