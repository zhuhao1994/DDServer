package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */
import java.util.List;
import org.apache.struts.action.ActionForm;

public class CartConfirmForm extends ActionForm {
    private static final long serialVersionUID = -5181092357624416800L;
    private List<?> addresses = null;
    private String address = null;
    private String payType = null;
    private String deliverPrice = null;
    private String invoiceType = null;
    private String invoiceContent = null;

    public CartConfirmForm() {
    }

    public List<?> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(List<?> addresses) {
        this.addresses = addresses;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeliverPrice() {
        return this.deliverPrice;
    }

    public void setDeliverPrice(String deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceContent() {
        return this.invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }
}
