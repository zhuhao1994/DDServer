package com.globalite.dangdang.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Bookcomment;
import com.globalite.dangdang.entity.Booktype;
import com.globalite.dangdang.entity.Images;
import com.globalite.dangdang.entity.Orderitems;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private static final long serialVersionUID = 5438652179728768054L;
    private Long id;
    private Images image;
    private Booktype booktype;
    private String name;
    private BigDecimal price;
    private BigDecimal commonprice;
    private BigDecimal vipprice;
    private Byte stockstatus;
    private String publisher;
    private String author;
    private String isbn;
    private Integer pagecount;
    private Integer wordcount;
    private String pagetype;
    private String introduction;
    private String catalog;
    private String editorialrecommend;
    private String images;
    private BigDecimal salesvolume;
    private Byte deleteflag;
    private Timestamp version;
    private String operator;
    private List<Orderitems> orderitemses = new ArrayList();
    private List<Bookcomment> bookcomments = new ArrayList();

    public Book(Long id) {
        this.id = id;
    }

    public Book() {
    }

    public Book(Images image, Booktype booktype, String name, BigDecimal price, BigDecimal commonprice, Byte stockstatus, String publisher, String isbn, BigDecimal salesvolume, Byte deleteflag) {
        this.image = image;
        this.booktype = booktype;
        this.name = name;
        this.price = price;
        this.commonprice = commonprice;
        this.stockstatus = stockstatus;
        this.publisher = publisher;
        this.isbn = isbn;
        this.salesvolume = salesvolume;
        this.deleteflag = deleteflag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booktype getBooktype() {
        return this.booktype;
    }

    public void setBooktype(Booktype booktype) {
        this.booktype = booktype;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCommonprice() {
        return this.commonprice;
    }

    public void setCommonprice(BigDecimal commonprice) {
        this.commonprice = commonprice;
    }

    public BigDecimal getVipprice() {
        return this.vipprice;
    }

    public void setVipprice(BigDecimal vipprice) {
        this.vipprice = vipprice;
    }

    public Byte getStockstatus() {
        return this.stockstatus;
    }

    public void setStockstatus(Byte stockstatus) {
        this.stockstatus = stockstatus;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPagecount() {
        return this.pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    public Integer getWordcount() {
        return this.wordcount;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public String getPagetype() {
        return this.pagetype;
    }

    public void setPagetype(String pagetype) {
        this.pagetype = pagetype;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEditorialrecommend() {
        return this.editorialrecommend;
    }

    public void setEditorialrecommend(String editorialrecommend) {
        this.editorialrecommend = editorialrecommend;
    }

    public BigDecimal getSalesvolume() {
        return this.salesvolume;
    }

    public void setSalesvolume(BigDecimal salesvolume) {
        this.salesvolume = salesvolume;
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

    public Images getImage() {
        return this.image;
    }

    public void setImage(Images image) {
        this.image = image;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<Orderitems> getOrderitemses() {
        return this.orderitemses;
    }

    public void setOrderitemses(List<Orderitems> orderitemses) {
        this.orderitemses = orderitemses;
    }

    public List<Bookcomment> getBookcomments() {
        return this.bookcomments;
    }

    public void setBookcomments(List<Bookcomment> bookcomments) {
        this.bookcomments = bookcomments;
    }
}
