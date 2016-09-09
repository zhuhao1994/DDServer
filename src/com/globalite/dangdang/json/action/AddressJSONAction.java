package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.AddressDao;
import com.globalite.dangdang.json.builder.AddressParamsBuilder;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.AddressRequestParams;
import com.oracle.dd.tool.json.response.entity.AddressFacadeResponseParams;
import java.util.List;

@JSONUrl(
        url = "/address.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.AddressJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.AddressJSONResponse"
)
public class AddressJSONAction implements JSONAction {
    private Builder builder = null;
    private JSONRequest<AddressRequestParams> jsonRequest = null;
    private JSONResponse<AddressFacadeResponseParams> jsonResponse = null;

    public AddressJSONAction() {
        this.builder = new AddressParamsBuilder();
    }

    public void setRequest(JSONRequest<AddressRequestParams> request) {
        this.jsonRequest = request;
    }

    public void setResponse(JSONResponse<AddressFacadeResponseParams> response) {
        this.jsonResponse = response;
    }

    public String handle(String req) throws JSONHanldException {
        try {
            AddressRequestParams e = (AddressRequestParams)this.jsonRequest.execute(req);
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else {
                AddressDao dao = (AddressDao)SpringManager.getManager().getBean("addressDao");
                List addresses = dao.findAllCustomerAddress(Long.valueOf(e.getCusid()));
                AddressFacadeResponseParams params = null;
                if(e.getOp() == null) {
                    params = (AddressFacadeResponseParams)this.builder.build(addresses, 0);
                } else {
                    params = (AddressFacadeResponseParams)this.builder.build(addresses, 1);
                }

                return this.jsonResponse.execute(params);
            }
        } catch (JSONFormatException var6) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}
