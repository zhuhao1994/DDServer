package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.json.builder.OrdersconfirmCartBuilder;
import com.globalite.dangdang.json.builder.OrdersconfirmRequestParamsBuilder;
import com.globalite.dangdang.service.CartConfirmService;
import com.globalite.dangdang.struts.web.form.CartConfirmForm;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.OrderConfirmRequestParams;
import com.oracle.dd.tool.json.response.entity.OrderconfirmResponseParams;

@JSONUrl(
        url = "/orderconfirm.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.OrderconfirmJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.BaseJSONResponse"
)
public class OrderconfirmJSONAction implements JSONAction {
    private Builder requestParamsBuilder = null;
    private Builder cartParamsBuilder = null;
    private JSONRequest<OrderConfirmRequestParams> jsonRequest = null;
    private JSONResponse<OrderconfirmResponseParams> jsonResponse = null;

    public OrderconfirmJSONAction() {
        this.requestParamsBuilder = new OrdersconfirmRequestParamsBuilder();
        this.cartParamsBuilder = new OrdersconfirmCartBuilder();
    }

    public void setRequest(JSONRequest<OrderConfirmRequestParams> request) {
        this.jsonRequest = request;
    }

    public void setResponse(JSONResponse<OrderconfirmResponseParams> response) {
        this.jsonResponse = response;
    }

    public String handle(String req) throws JSONHanldException {
        try {
            OrderConfirmRequestParams e = (OrderConfirmRequestParams)this.jsonRequest.execute(req);
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else if(e.getCusid() == null) {
                throw new JSONHanldException("无法获取必要的客户编号，无法查询订单");
            } else {
                CartConfirmForm vo = (CartConfirmForm)this.requestParamsBuilder.build(e, -1);
                ShoppingCart cart = (ShoppingCart)this.cartParamsBuilder.build(e, -1);
                Customer customer = new Customer(Long.valueOf(e.getCusid()));
                CartConfirmService service = (CartConfirmService)SpringManager.getManager().getBean("cartService");
                boolean isOk = true;

                try {
                    service.doConfirmOrder(cart, vo, customer);
                } catch (Exception var9) {
                    var9.printStackTrace();
                    isOk = false;
                }

                OrderconfirmResponseParams responseParams = new OrderconfirmResponseParams();
                responseParams.setIsOk(isOk);
                return this.jsonResponse.execute(responseParams);
            }
        } catch (JSONFormatException var10) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}
