package com.globalite.dangdang.framework;

/**
 * Created by zhu on 2016/9/5.
 */
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class WebJSONAction implements JSONAction {
    private HttpServletRequest servletRequest = null;
    private HttpServletResponse servletResponse = null;
    private ServletContext servletContext = null;
    private HttpSession session = null;
    private JSONRequest<Object> request = null;
    private JSONResponse<Object> response = null;

    public WebJSONAction() {
    }

    public abstract String doBusiness(JSONRequest<Object> var1, JSONResponse<Object> var2, String var3) throws JSONHanldException;

    public String handle(String jsonRequestParams) throws JSONHanldException {
        if(this.request != null && this.response != null) {
            return this.doBusiness(this.request, this.response, jsonRequestParams);
        } else {
            throw new JSONHanldException("没有JSON请求处理器与JSON响应处理器，请检查是否设置相关注解");
        }
    }

    public HttpServletRequest getServletRequest() {
        return this.servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public HttpServletResponse getServletResponse() {
        return this.servletResponse;
    }

    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public JSONRequest<Object> getRequest() {
        return this.request;
    }

    public void setRequest(JSONRequest<Object> request) {
        this.request = request;
    }

    public JSONResponse<Object> getResponse() {
        return this.response;
    }

    public void setResponse(JSONResponse<Object> response) {
        this.response = response;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public HttpSession getSession() {
        return this.session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
