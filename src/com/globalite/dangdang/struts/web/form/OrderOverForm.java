package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OrderOverForm extends ActionForm {
    private static final long serialVersionUID = -1289851822818870801L;

    public OrderOverForm() {
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }
}

