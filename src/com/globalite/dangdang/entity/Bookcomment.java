package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Customer;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Bookcomment implements Serializable {
    private static final long serialVersionUID = 1461730574165178548L;
    private Long id;
    private Customer customer;
    private Book book;
    private String content;
    private Date contentdate;
    private Byte star;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;

    public Bookcomment() {
    }

    public Bookcomment(Customer customer, Book book, String content, Date contentdate, Byte star, Byte deleteflag) {
        this.customer = customer;
        this.book = book;
        this.content = content;
        this.contentdate = contentdate;
        this.star = star;
        this.deleteflag = deleteflag;
    }

    public Bookcomment(Customer customer, Book book, String content, Date contentdate, Byte star, Byte deleteflag, Timestamp version, String operator) {
        this.customer = customer;
        this.book = book;
        this.content = content;
        this.contentdate = contentdate;
        this.star = star;
        this.deleteflag = deleteflag;
        this.version = version;
        this.operator = operator;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getContentdate() {
        return this.contentdate;
    }

    public void setContentdate(Date contentdate) {
        this.contentdate = contentdate;
    }

    public Byte getStar() {
        return this.star;
    }

    public void setStar(Byte star) {
        this.star = star;
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
