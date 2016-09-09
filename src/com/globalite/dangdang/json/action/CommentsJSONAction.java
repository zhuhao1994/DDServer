package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.json.builder.CommentsParamsBuilder;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.CommentsRequestParams;
import com.oracle.dd.tool.json.response.entity.CommentsResponseParams;
import java.util.List;

@JSONUrl(
        url = "/comments.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.CommentsJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.BaseJSONResponse"
)
public class CommentsJSONAction implements JSONAction {
    private Builder builder = null;
    private JSONRequest<CommentsRequestParams> jsonRequest = null;
    private JSONResponse<List<CommentsResponseParams>> jsonResponse = null;

    public CommentsJSONAction() {
        this.builder = new CommentsParamsBuilder();
    }

    public void setRequest(JSONRequest<CommentsRequestParams> request) {
        this.jsonRequest = request;
    }

    public void setResponse(JSONResponse<List<CommentsResponseParams>> response) {
        this.jsonResponse = response;
    }

    public String handle(String req) throws JSONHanldException {
        try {
            CommentsRequestParams e = (CommentsRequestParams)this.jsonRequest.execute(req);
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else {
                BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
                Book book = dao.findFullBook(Long.valueOf(e.getBookid()), false);
                List params = (List)this.builder.build(book, -1);
                return this.jsonResponse.execute(params);
            }
        } catch (JSONFormatException var6) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}

