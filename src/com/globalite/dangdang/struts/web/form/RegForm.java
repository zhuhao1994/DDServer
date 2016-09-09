package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */
import org.apache.struts.action.ActionForm;

public class RegForm extends ActionForm {
    private static final long serialVersionUID = -3575154585156222782L;
    private String flag = null;
    private String email = null;
    private String password = null;
    private String repassword = null;
    private String name = null;
    private String regname = null;
    private String mobile = null;
    private String code = null;
    private String agree = "1";

    public RegForm() {
    }

    public String getRegname() {
        return this.regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getAgree() {
        return this.agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getRepassword() {
        return this.repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
