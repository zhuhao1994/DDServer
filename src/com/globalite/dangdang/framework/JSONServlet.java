package com.globalite.dangdang.framework;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.framework.WebJSONAction;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONAction;
import com.oracle.dd.tool.json.framework.JSONAnnotationManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class JSONServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String JSON_ERROR = "\"errinfo\"={\"id\":\"%s\",\"msg\":\"%s\"}";
    private Logger logger = Logger.getLogger(JSONServlet.class);
    private String jsonActionPackege = null;
    private JSONAnnotationManager annotationManager = null;
    public static final String JSON_REQUEST_PARAMS_KEY = "params";

    public JSONServlet() {
    }

    public void init(ServletConfig config) throws ServletException {
        this.logger.info("JSONServlet创建中.....");
        super.init(config);
        this.jsonActionPackege = config.getInitParameter("jsonActionPackege");
        this.logger.info("配置的JSONAction控制组件包名:[" + this.jsonActionPackege + "]");
        this.logger.info("创建JSONAnnotationManager对象，并构建所有JSONAction对象");
        if(this.jsonActionPackege == null) {
            this.annotationManager = JSONAnnotationManager.getInstance();
        } else {
            this.annotationManager = JSONAnnotationManager.getInstance(this.jsonActionPackege);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doJSON(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doJSON(request, response);
    }

    private void setWebJSONActionParams(WebJSONAction action, HttpServletRequest request, HttpServletResponse response) {
        action.setServletRequest(request);
        action.setServletResponse(response);
        action.setServletContext(super.getServletContext());
        action.setSession(request.getSession());
    }

    private void doJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        String url = request.getRequestURI();
        this.logger.info("请求的url:[" + url + "]");
        String params = request.getParameter("params");
        this.logger.info("请求的url:[" + url + "]对应的json数据是[" + params + "]");
        JSONAction action = this.annotationManager.getAction(url);
        if(action == null) {
            this.logger.warn("请求的url:[" + url + "]没有匹配到合适的JSONAction");
        } else {
            this.logger.info("请求的url:[" + url + "]匹配到的JSONAction是[" + action.getClass().getName() + "]");
            if(action instanceof WebJSONAction) {
                this.logger.info("请求的url:[" + url + "]匹配到的JSONAction是WebJSONAction类型");
                this.setWebJSONActionParams((WebJSONAction)action, request, response);
            }

            String msg;
            try {
                String e = action.handle(params);
                this.logger.info("请求的url:[" + url + "]获取的jso响应数据是[" + e + "]");
                if(e == null) {
                    msg = MessageFormat.format("\"errinfo\"={\"id\":\"%s\",\"msg\":\"%s\"}", new Object[]{"E-000", "无法解析请求数据"});
                    this.sendResponse(response, msg);
                    return;
                }

                this.sendResponse(response, e);
            } catch (JSONHanldException var8) {
                var8.printStackTrace();
                msg = String.format("\"errinfo\"={\"id\":\"%s\",\"msg\":\"%s\"}", new Object[]{"E-000", var8.getMessage()});
                this.sendResponse(response, msg);
                this.logger.error("处理JSON请求时出错，消息:" + var8.getMessage());
            }

        }
    }

    private void sendResponse(HttpServletResponse response, String responseParams) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(responseParams);
        pw.close();
    }
}

