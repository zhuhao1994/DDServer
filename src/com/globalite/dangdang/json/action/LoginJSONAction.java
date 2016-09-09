package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.framework.WebJSONAction;
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.json.builder.LoginParamsBuilder;
import com.globalite.dangdang.service.LoginServiceImpl;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.LoginRequestParams;
import com.oracle.dd.tool.json.response.entity.LoginResponseParams;

@JSONUrl(
        url = "/login.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.LoginJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.BaseJSONResponse"
)
public class LoginJSONAction extends WebJSONAction {
    private Builder builder = null;

    public LoginJSONAction() {
        this.builder = new LoginParamsBuilder();
    }

    public String doBusiness(JSONRequest<Object> jsonRequest, JSONResponse<Object> jsonResponse, String jsonRequestParams) throws JSONHanldException {
        try {
            LoginRequestParams e = (LoginRequestParams)jsonRequest.execute(jsonRequestParams);
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else {
                LoginResponseParams respParams = null;
                String number = (String)super.getSession().getAttribute("com.globalite.dangdang.constants.checknumber");
                LoginServiceImpl service = new LoginServiceImpl();
                if(!service.checkNumber(number, e.getCode())) {
                    respParams = (LoginResponseParams)this.builder.build((Object)null, 2);
                    return jsonResponse.execute(respParams);
                } else {
                    Customer cus = service.checkLogin(e.getUid(), e.getPwd());
                    if(cus == null) {
                        respParams = (LoginResponseParams)this.builder.build((Object)null, 1);
                        return jsonResponse.execute(respParams);
                    } else {
                        respParams = (LoginResponseParams)this.builder.build(cus, 3);
                        return jsonResponse.execute(respParams);
                    }
                }
            }
        } catch (JSONFormatException var9) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}
