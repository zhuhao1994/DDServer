package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.OrdersDao;
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.entity.decorator.OrdersDecorator;
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.json.builder.OrdersParamsBuilder;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.OrdersRequestParams;
import com.oracle.dd.tool.json.response.entity.OrdersResponseParams;
import java.util.ArrayList;
import java.util.List;

@JSONUrl(
        url = "/orders.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.OrdersJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.BaseJSONResponse"
)
public class OrdersJSONAction implements JSONAction {
    private Builder builder = null;
    private JSONRequest<OrdersRequestParams> jsonRequest = null;
    private JSONResponse<List<OrdersResponseParams>> jsonResponse = null;

    public OrdersJSONAction() {
        this.builder = new OrdersParamsBuilder();
    }

    public void setRequest(JSONRequest<OrdersRequestParams> request) {
        this.jsonRequest = request;
    }

    public void setResponse(JSONResponse<List<OrdersResponseParams>> response) {
        this.jsonResponse = response;
    }

    public String handle(String req) throws JSONHanldException {
        try {
            OrdersRequestParams e = (OrdersRequestParams)this.jsonRequest.execute(req);
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else if(e.getCusid() == null) {
                throw new JSONHanldException("无法获取必要的客户编号，无法查询订单");
            } else {
                OrdersDao dao = (OrdersDao)SpringManager.getManager().getBean("ordersDao");
                List orders = dao.findOrdersByCondition(Long.valueOf(e.getCusid()));
                ArrayList ret = new ArrayList();

                for(int param = 0; param < orders.size(); ++param) {
                    ret.add(new OrdersDecorator((Orders)orders.get(param)));
                }

                List var8 = (List)this.builder.build(ret, -1);
                return this.jsonResponse.execute(var8);
            }
        } catch (JSONFormatException var7) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}
