package com.globalite.dangdang.struts.web.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.OrdersDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.entity.decorator.OrdersDecorator;
import com.globalite.dangdang.struts.web.form.OrdersForm;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OrdersAction extends DispatchAction {
    private OrdersDao dao = null;

    public OrdersAction() {
    }

    public void setDao(OrdersDao dao) {
        this.dao = dao;
    }

    private List<OrdersDecorator> findOrders(OrdersForm vo) {
        List orders = this.dao.findOrdersByCondition(vo);
        if(orders.size() == 0) {
            vo.setMsg("没有找到任何属于您的订单,当当网全场满70免运费");
            return null;
        } else {
            ArrayList ret = new ArrayList();

            for(int i = 0; i < orders.size(); ++i) {
                ret.add(new OrdersDecorator((Orders)orders.get(i)));
            }

            return ret;
        }
    }

    private void updateOrdersType(OrdersForm vo) {
        this.dao.updateOrdersType(Long.valueOf(vo.getId()), Byte.valueOf(vo.getOrderstype()));
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        if(cus == null) {
            request.setAttribute("com.globalite.dangdang.constants.okurl", "orders.do?op=load");
            return mapping.findForward("login");
        } else {
            OrdersForm vo = (OrdersForm)form;
            vo.setCusid(cus.getId());
            vo.setOrders(this.findOrders(vo));
            return mapping.findForward("default");
        }
    }

    public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        OrdersForm vo = (OrdersForm)form;
        vo.setCusid(cus.getId());
        vo.setOrders(this.findOrders(vo));
        return mapping.findForward("default");
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OrdersForm vo = (OrdersForm)form;
        this.updateOrdersType(vo);
        this.query(mapping, form, request, response);
        return mapping.findForward("default");
    }

    public ActionForward undo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        OrdersForm vo = (OrdersForm)form;
        this.updateOrdersType(vo);
        this.query(mapping, form, request, response);
        return mapping.findForward("default");
    }
}
