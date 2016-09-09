package com.globalite.dangdang.struts2.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.Customer;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class EditPasswordAction implements Action {
    private String oldPwd = null;
    private String newPwd = null;
    private String newPwdAgain = null;
    private String message = null;
    private CustomerDao dao = null;

    public EditPasswordAction() {
    }

    public void setDao(CustomerDao dao) {
        this.dao = dao;
    }

    public String getOldPwd() {
        return this.oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return this.newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getNewPwdAgain() {
        return this.newPwdAgain;
    }

    public void setNewPwdAgain(String newPwdAgain) {
        this.newPwdAgain = newPwdAgain;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute() throws Exception {
        if(ActionContext.getContext().getParameters().get("op") != null) {
            return "err";
        } else {
            Customer cus = (Customer)ActionContext.getContext().getSession().get("com.globalite.dangdang.constants.customername");
            if(!this.checkOldPassword(cus.getRegname(), this.getOldPwd()).booleanValue()) {
                this.setMessage("输入的旧密码不正确，请重新输入！");
                return "err";
            } else {
                this.updatePassword(cus.getId(), this.getNewPwd());
                cus.setPassword(this.getNewPwd());
                return "ok";
            }
        }
    }

    private void updatePassword(Long cusid, String newpwd) {
        this.dao.updatePassword(cusid, newpwd);
    }

    private Boolean checkOldPassword(String regname, String oldpwd) {
        Customer cus = this.dao.findCustomer(regname, oldpwd);
        return Boolean.valueOf(cus != null);
    }
}

