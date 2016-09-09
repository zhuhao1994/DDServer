package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.oracle.dd.cache.CacheCartManager;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction extends HttpServlet {
    private static final long serialVersionUID = -4163169660955309204L;

    public void destroy() {
        super.destroy();
        CacheCartManager.getInstance().close();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringManager.createManager(config.getInitParameter("spring_config"));
    }

    public IndexAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
