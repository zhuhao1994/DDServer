package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Consignmentaddress;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Orderitems;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    private static final long serialVersionUID = 4282033706889984059L;
    private Long id;
    private Consignmentaddress consignmentaddress;
    private Customer customer;
    private String ordid;
    private String receiver;
    private String postcode;
    private String phone;
    private String mobile;
    private Byte paytype;
    private Byte invoicetype;
    private Byte invoicecontent;
    private BigDecimal price;
    private BigDecimal deliverprice;
    private Date createdate;
    private Byte ordertype;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;
    private List<Orderitems> orderitemses = new ArrayList();
    private List<Book> books = new ArrayList();

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Orders() {
    }

    public Orders(Consignmentaddress consignmentaddress, Customer customer, String ordid, String receiver, String postcode, Byte paytype, Byte invoicetype, BigDecimal price, BigDecimal deliverprice, Date createdate, Byte ordertype, Byte deleteflag) {
        this.consignmentaddress = consignmentaddress;
        this.customer = customer;
        this.ordid = ordid;
        this.receiver = receiver;
        this.postcode = postcode;
        this.paytype = paytype;
        this.invoicetype = invoicetype;
        this.price = price;
        this.deliverprice = deliverprice;
        this.createdate = createdate;
        this.ordertype = ordertype;
        this.deleteflag = deleteflag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consignmentaddress getConsignmentaddress() {
        return this.consignmentaddress;
    }

    public void setConsignmentaddress(Consignmentaddress consignmentaddress) {
        this.consignmentaddress = consignmentaddress;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrdid() {
        return this.ordid;
    }

    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public Byte getPaytype() {
        return this.paytype;
    }

    public void setPaytype(Byte paytype) {
        this.paytype = paytype;
    }

    public Byte getInvoicetype() {
        return this.invoicetype;
    }

    public void setInvoicetype(Byte invoicetype) {
        this.invoicetype = invoicetype;
    }

    public Byte getInvoicecontent() {
        return this.invoicecontent;
    }

    public void setInvoicecontent(Byte invoicecontent) {
        this.invoicecontent = invoicecontent;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDeliverprice() {
        return this.deliverprice;
    }

    public void setDeliverprice(BigDecimal deliverprice) {
        this.deliverprice = deliverprice;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Byte getOrdertype() {
        return this.ordertype;
    }

    public void setOrdertype(Byte ordertype) {
        this.ordertype = ordertype;
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

    public List<Orderitems> getOrderitemses() {
        return this.orderitemses;
    }

    public void setOrderitemses(List<Orderitems> orderitemses) {
        this.orderitemses = orderitemses;
    }
}

