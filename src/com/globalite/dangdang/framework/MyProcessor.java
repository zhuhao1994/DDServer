package com.globalite.dangdang.framework;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.RequestProcessor;

public class MyProcessor extends RequestProcessor {
    public MyProcessor() {
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        super.process(request, response);
    }
}
