package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Orderitems;
import com.globalite.dangdang.entity.decorator.OrdersDecorator;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.OrderitemResponseParams;
import com.oracle.dd.tool.json.response.entity.OrdersResponseParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrdersParamsBuilder implements Builder {
    public OrdersParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        List orders = (List)entity;
        ArrayList params = new ArrayList();
        Iterator var6 = orders.iterator();

        while(var6.hasNext()) {
            OrdersDecorator order = (OrdersDecorator)var6.next();
            OrdersResponseParams param = new OrdersResponseParams();
            param.setOrdid(order.getOwn().getOrdid());
            param.setOrdertype(order.getTypeString());
            param.setCreatedate(order.getCreateDate());
            param.setPrice(order.getPrice());
            List items = order.getOwn().getOrderitemses();
            ArrayList itemParams = new ArrayList();
            Iterator var11 = items.iterator();

            while(var11.hasNext()) {
                Orderitems item = (Orderitems)var11.next();
                OrderitemResponseParams itemParam = new OrderitemResponseParams();
                itemParam.setBookImageName(item.getBook().getImage().getName());
                itemParam.setBookName(item.getBook().getName());
                itemParam.setOrderItemId(String.valueOf(item.getId()));
                itemParam.setQuantity(String.valueOf(item.getQuantity()));
                itemParam.setSubTotal(String.valueOf(item.getSubtotal()));
                itemParams.add(itemParam);
            }

            param.setOrderitems(itemParams);
            params.add(param);
        }

        return params;
    }
}

