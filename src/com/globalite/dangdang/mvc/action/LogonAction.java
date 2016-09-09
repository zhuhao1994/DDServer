package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */

import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.service.CartServiceAdapter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogonAction extends HttpServlet {
    private static final long serialVersionUID = 574470444569263410L;

    public LogonAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String regName = ((Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername")).getRegname();
        CartServiceAdapter cartService = new CartServiceAdapter(request, regName);
        cartService.removeCart();
        request.getSession().removeAttribute("com.globalite.dangdang.constants.checknumber");
        request.getSession().removeAttribute("com.globalite.dangdang.constants.customername");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

