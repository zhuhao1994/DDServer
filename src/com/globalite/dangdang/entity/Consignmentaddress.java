package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.City;
import com.globalite.dangdang.entity.Customer;
import java.io.Serializable;
import java.sql.Timestamp;

public class Consignmentaddress implements Serializable {
    private static final long serialVersionUID = -6586724954335553800L;
    private Long id;
    private City cityByDistrict;
    private Customer customer;
    private City cityByCountry;
    private City cityByCity;
    private City cityByProvince;
    private String consignmentname;
    private String address;
    private String postcode;
    private String phone;
    private String mobile;
    private Byte default_;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;

    public Consignmentaddress(Long id) {
        this.id = id;
    }

    public Consignmentaddress() {
    }

    public Consignmentaddress(City cityByDistrict, Customer customer, City cityByCountry, City cityByCity, City cityByProvince, String consignmentname, String address, String postcode, Byte deleteflag) {
        this.cityByDistrict = cityByDistrict;
        this.customer = customer;
        this.cityByCountry = cityByCountry;
        this.cityByCity = cityByCity;
        this.cityByProvince = cityByProvince;
        this.consignmentname = consignmentname;
        this.address = address;
        this.postcode = postcode;
        this.deleteflag = deleteflag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCityByDistrict() {
        return this.cityByDistrict;
    }

    public void setCityByDistrict(City cityByDistrict) {
        this.cityByDistrict = cityByDistrict;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public City getCityByCountry() {
        return this.cityByCountry;
    }

    public void setCityByCountry(City cityByCountry) {
        this.cityByCountry = cityByCountry;
    }

    public City getCityByCity() {
        return this.cityByCity;
    }

    public void setCityByCity(City cityByCity) {
        this.cityByCity = cityByCity;
    }

    public City getCityByProvince() {
        return this.cityByProvince;
    }

    public void setCityByProvince(City cityByProvince) {
        this.cityByProvince = cityByProvince;
    }

    public String getConsignmentname() {
        return this.consignmentname;
    }

    public void setConsignmentname(String consignmentname) {
        this.consignmentname = consignmentname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getDefault_() {
        return this.default_;
    }

    public void setDefault_(Byte default_) {
        this.default_ = default_;
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

