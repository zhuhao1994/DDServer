package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Orders;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orderitems implements Serializable {
    private static final long serialVersionUID = -236530316235562872L;
    private Long id;
    private Book book;
    private Orders orders;
    private Integer quantity;
    private BigDecimal subtotal;
    private Timestamp version;
    private String operator;

    public Orderitems() {
    }

    public Orderitems(Book book, Orders orders, Integer quantity, BigDecimal subtotal) {
        this.book = book;
        this.orders = orders;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Orderitems(Book book, Orders orders, Integer quantity, BigDecimal subtotal, Timestamp version, String operator) {
        this.book = book;
        this.orders = orders;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.version = version;
        this.operator = operator;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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

