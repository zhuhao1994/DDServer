package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.service.LoginServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends HttpServlet {
    private static final long serialVersionUID = -5604253371602701846L;

    public LoginAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
        String number = (String)request.getSession().getAttribute("com.globalite.dangdang.constants.checknumber");
        String code = request.getParameter("code");
        LoginServiceImpl service = new LoginServiceImpl();
        if(!service.checkNumber(number, code)) {
            request.setAttribute("com.globalite.dangdang.constants.errorkey", "输入的验证码不正确");
            this.forward(request, response, true);
        } else {
            Customer cus = service.checkLogin(uid, pwd);
            if(cus == null) {
                request.setAttribute("com.globalite.dangdang.constants.errorkey", "用户名或者密码不正确");
                this.forward(request, response, true);
            } else {
                request.getSession().setAttribute("com.globalite.dangdang.constants.customername", cus);
                this.forward(request, response, false);
            }
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, boolean isError) throws ServletException, IOException {
        String okUrl = this.getUrl("com.globalite.dangdang.constants.okurl", request);
        String errUrl = this.getUrl("com.globalite.dangdang.constants.errurl", request);
        if(okUrl == null) {
            okUrl = "/books.jhtml";
        }

        if(errUrl == null) {
            errUrl = "/WEB-INF/jsp/login.jsp";
        }

        if(isError) {
            request.getRequestDispatcher(errUrl).forward(request, response);
        } else {
            request.getRequestDispatcher(okUrl).forward(request, response);
        }

    }

    private String getUrl(String key, HttpServletRequest request) {
        String url = request.getParameter(key);
        if(url != null && !"".equals(url.trim())) {
            request.setAttribute(key, url);
            return url;
        } else {
            url = (String)request.getAttribute(key);
            if(url != null && !"".equals(url.trim())) {
                return url;
            } else {
                url = (String)request.getSession().getAttribute(key);
                if(url != null && !"".equals(url.trim())) {
                    request.getSession().removeAttribute(key);
                    return url;
                } else {
                    return url;
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

