package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.struts.web.form.CartConfirmForm;
import com.oracle.dd.tool.json.request.entity.OrderConfirmRequestParams;

public class OrdersconfirmRequestParamsBuilder implements Builder {
    public OrdersconfirmRequestParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        OrderConfirmRequestParams requestPrams = (OrderConfirmRequestParams)entity;
        CartConfirmForm vo = new CartConfirmForm();
        vo.setAddress(requestPrams.getAddressid());
        vo.setDeliverPrice(requestPrams.getDeliverprice());
        vo.setInvoiceContent(requestPrams.getInvoicecontent());
        vo.setInvoiceType(requestPrams.getInvoicetype());
        vo.setPayType(requestPrams.getPaytype());
        return vo;
    }
}
