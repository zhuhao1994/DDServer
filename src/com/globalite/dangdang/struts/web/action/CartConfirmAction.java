package com.globalite.dangdang.struts.web.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.AddressDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.service.CartConfirmService;
import com.globalite.dangdang.service.CartServiceAdapter;
import com.globalite.dangdang.struts.web.form.CartConfirmForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CartConfirmAction extends DispatchAction {
    private CartConfirmService service = null;

    public CartConfirmAction() {
    }

    public void setService(CartConfirmService service) {
        this.service = service;
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CartConfirmForm vo = (CartConfirmForm)form;
        String regName = ((Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername")).getRegname();
        if(regName == null) {
            return mapping.findForward("error");
        } else {
            CartServiceAdapter cartService = new CartServiceAdapter(request, regName);
            ShoppingCart cart = cartService.getCart();
            request.setAttribute("com.globalite.dangdang.constants.shoppingcart", cart);
            AddressDao dao = (AddressDao)SpringManager.getManager().getBean("addressDao");
            Long id = ((Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername")).getId();
            vo.setAddresses(dao.findAllCustomerAddress(id));
            return mapping.findForward("default");
        }
    }

    public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CartConfirmForm vo = (CartConfirmForm)form;
        String regName = ((Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername")).getRegname();
        if(regName == null) {
            return mapping.findForward("error");
        } else {
            CartServiceAdapter cartService = new CartServiceAdapter(request, regName);
            Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
            this.service.doConfirmOrder(cartService.getCart(), vo, cus);
            double total = Double.valueOf(vo.getDeliverPrice()).doubleValue() + cartService.getCart().getTotalPrice().doubleValue();
            request.setAttribute("total", Double.valueOf(total));
            return mapping.findForward("ok");
        }
    }
}
