package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderConfirmAction extends HttpServlet {
    private static final long serialVersionUID = 3619022920228537584L;

    public OrderConfirmAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        if(request.getSession().getAttribute("com.globalite.dangdang.constants.customername") == null) {
            request.setAttribute("com.globalite.dangdang.constants.okurl", "cartConfirm.do?op=load");
            request.getRequestDispatcher("IndexServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("cartConfirm.do?op=load").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
