package com.globalite.dangdang.struts.web.action;

/**
 * Created by zhu on 2016/9/5.
 */

import com.globalite.dangdang.common.Utils;
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Customerlevel;
import com.globalite.dangdang.struts.web.form.RegForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class RegAction extends Action {
    private CustomerDao dao = null;

    public RegAction() {
    }

    public void setDao(CustomerDao dao) {
        this.dao = dao;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        RegForm regForm = (RegForm)form;
        if(regForm.getFlag() != null) {
            return mapping.findForward("default");
        } else if(!this.checkNumber(request, regForm)) {
            request.setAttribute("reg_err_code", "输入的验证码不正确");
            return mapping.findForward("default");
        } else {
            Customer cus = this.getCustomer(regForm);
            if(this.isRegnameExisted(cus.getRegname()).booleanValue()) {
                ActionMessages msg = new ActionMessages();
                msg.add("reg_err_regname", new ActionMessage("err.info.reg.regname.existed"));
                super.saveErrors(request, msg);
                return mapping.findForward("default");
            } else {
                this.dao.saveCustomer(cus);
                return mapping.findForward("ok");
            }
        }
    }

    private boolean checkNumber(HttpServletRequest request, RegForm regForm) {
        boolean ret = false;
        String number = (String)request.getSession().getAttribute("com.globalite.dangdang.constants.checknumber");
        return number == null?ret:(!number.equalsIgnoreCase(regForm.getCode())?ret:true);
    }

    private Boolean isRegnameExisted(String regname) {
        List regnames = this.dao.getAllRegnames();
        return regnames != null && regnames.size() > 0?Boolean.valueOf(regnames.contains(regname)):Boolean.valueOf(false);
    }

    private Customer getCustomer(RegForm regForm) {
        Customer cus = new Customer();
        cus.setEmail(regForm.getEmail());
        cus.setRegname(regForm.getRegname());
        cus.setName(regForm.getName());
        cus.setMobile(regForm.getMobile());
        cus.setPassword(Utils.toMD5(regForm.getPassword()));
        cus.setDeleteflag(Byte.valueOf((byte)0));
        cus.setCustomerlevel(new Customerlevel(Long.valueOf(1L)));
        return cus;
    }
}
