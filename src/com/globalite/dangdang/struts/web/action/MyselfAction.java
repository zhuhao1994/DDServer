package com.globalite.dangdang.struts.web.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.OrdersDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.struts.web.form.MyselfForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MyselfAction extends DispatchAction {
    private OrdersDao dao = null;

    public MyselfAction() {
    }

    public void setDao(OrdersDao dao) {
        this.dao = dao;
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        MyselfForm vo = (MyselfForm)form;
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        if(cus == null) {
            request.setAttribute("com.globalite.dangdang.constants.okurl", "myself.do?op=load");
            return mapping.findForward("login");
        } else {
            vo.setCustomer(cus);
            this.doOrdersStatistic(vo);
            return mapping.findForward("default");
        }
    }

    private void doOrdersStatistic(MyselfForm vo) {
        List orders = this.dao.findOrdersByCondition(vo.getCustomer().getId());
        Integer type_verify = Integer.valueOf(0);
        Integer type_store = Integer.valueOf(0);
        Integer type_send = Integer.valueOf(0);
        Integer type_over = Integer.valueOf(0);
        Integer type_cancel = Integer.valueOf(0);

        for(int i = 0; i < orders.size(); ++i) {
            Orders order = (Orders)orders.get(i);
            switch(order.getOrdertype().byteValue()) {
                case 0:
                    type_verify = Integer.valueOf(type_verify.intValue() + 1);
                    break;
                case 1:
                    type_store = Integer.valueOf(type_store.intValue() + 1);
                    break;
                case 2:
                    type_send = Integer.valueOf(type_send.intValue() + 1);
                    break;
                case 3:
                    type_over = Integer.valueOf(type_over.intValue() + 1);
                    break;
                case 4:
                    type_cancel = Integer.valueOf(type_cancel.intValue() + 1);
            }
        }

        vo.setType_verify(String.valueOf(type_verify));
        vo.setType_store(String.valueOf(type_store));
        vo.setType_send(String.valueOf(type_send));
        vo.setType_over(String.valueOf(type_over));
        vo.setType_cancel(String.valueOf(type_cancel));
    }
}
