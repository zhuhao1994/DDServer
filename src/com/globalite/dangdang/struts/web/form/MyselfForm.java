package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Customer;
import org.apache.struts.action.ActionForm;

public class MyselfForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private Customer customer = new Customer();
    private String type_verify = null;
    private String type_store = null;
    private String type_send = null;
    private String type_over = null;
    private String type_cancel = null;

    public MyselfForm() {
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType_verify() {
        return this.type_verify;
    }

    public void setType_verify(String typeVerify) {
        this.type_verify = typeVerify;
    }

    public String getType_store() {
        return this.type_store;
    }

    public void setType_store(String typeStore) {
        this.type_store = typeStore;
    }

    public String getType_send() {
        return this.type_send;
    }

    public void setType_send(String typeSend) {
        this.type_send = typeSend;
    }

    public String getType_over() {
        return this.type_over;
    }

    public void setType_over(String typeOver) {
        this.type_over = typeOver;
    }

    public String getType_cancel() {
        return this.type_cancel;
    }

    public void setType_cancel(String typeCancel) {
        this.type_cancel = typeCancel;
    }
}
