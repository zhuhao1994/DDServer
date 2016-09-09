package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.json.builder.BooksParamsBuilder;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.BooksRequestParams;
import com.oracle.dd.tool.json.response.entity.BooksResponseParams;
import java.util.List;

@JSONUrl(
        url = "/books.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.BooksJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.BaseJSONResponse"
)
public class BooksJSONAction implements JSONAction {
    private Builder builder = null;
    private JSONRequest<BooksRequestParams> jsonRequest = null;
    private JSONResponse<List<BooksResponseParams>> jsonResponse = null;

    public BooksJSONAction() {
        this.builder = new BooksParamsBuilder();
    }

    public void setRequest(JSONRequest<BooksRequestParams> request) {
        this.jsonRequest = request;
    }

    public void setResponse(JSONResponse<List<BooksResponseParams>> response) {
        this.jsonResponse = response;
    }

    public String handle(String req) throws JSONHanldException {
        try {
            BooksRequestParams e = (BooksRequestParams)this.jsonRequest.execute(req);
            String name = null;
            if(e != null) {
                name = e.getName();
            }

            BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
            List books = dao.findBooks(name);
            List params = (List)this.builder.build(books, -1);
            return this.jsonResponse.execute(params);
        } catch (JSONFormatException var7) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }
}

