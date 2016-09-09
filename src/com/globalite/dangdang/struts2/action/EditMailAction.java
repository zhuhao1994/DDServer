package com.globalite.dangdang.struts2.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.Customer;
import com.opensymphony.xwork2.ActionContext;

public class EditMailAction {
    private String currentMail = null;
    private String password = null;
    private String newMail = null;
    private String message = null;
    private CustomerDao dao = null;

    public EditMailAction() {
    }

    public void setDao(CustomerDao dao) {
        this.dao = dao;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentMail() {
        return this.currentMail;
    }

    public void setCurrentMail(String currentMail) {
        this.currentMail = currentMail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewMail() {
        return this.newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }

    public String load() {
        this.setCurrentMail(this.getEMail());
        return "default";
    }

    public String save() {
        Customer cus = (Customer)ActionContext.getContext().getSession().get("com.globalite.dangdang.constants.customername");
        if(!this.checkPassword(cus.getRegname(), this.getPassword()).booleanValue()) {
            this.setMessage("输入的旧密码不正确，请重新输入！");
            return "default";
        } else {
            this.updateEMail(cus.getId(), this.getNewMail());
            cus.setEmail(this.getNewMail());
            return "ok";
        }
    }

    private String getEMail() {
        Customer cus = (Customer)ActionContext.getContext().getSession().get("com.globalite.dangdang.constants.customername");
        return cus.getEmail();
    }

    private void updateEMail(Long cusid, String mail) {
        CustomerDao dao = (CustomerDao)SpringManager.getManager().getBean("customerDao");
        dao.updateEMail(cusid, mail);
    }

    private Boolean checkPassword(String regname, String oldpwd) {
        Customer cus = this.dao.findCustomer(regname, oldpwd);
        return Boolean.valueOf(cus != null);
    }
}
