package com.globalite.dangdang.filter;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter {
    private String isRun = null;
    private String permits = null;
    private String userConstants = null;
    private String wrongUrl = null;

    public UserFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        if(this.isRun == null) {
            chain.doFilter(request, response);
        } else if("N".equalsIgnoreCase(this.isRun)) {
            chain.doFilter(request, response);
        } else if(this.checkPermits(req)) {
            chain.doFilter(request, response);
        } else if(this.checkUser(req)) {
            chain.doFilter(request, response);
        } else if(this.wrongUrl == null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(this.wrongUrl).forward(req, res);
        }
    }

    private boolean checkUser(HttpServletRequest request) {
        Object o = request.getSession().getAttribute(this.userConstants);
        return o != null;
    }

    private boolean checkPermits(HttpServletRequest request) {
        if(this.permits == null) {
            return false;
        } else {
            String url = request.getRequestURI();
            String[] permitParams = this.permits.split("[;]");
            String[] var7 = permitParams;
            int var6 = permitParams.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String permit = var7[var5];
                if(this.isPermit(request.getContextPath() + permit, url)) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean isPermit(String permit, String url) {
        try {
            Pattern e = Pattern.compile("^" + permit + "$");
            return e.matcher(url).matches();
        } catch (Exception var4) {
            return false;
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.isRun = config.getInitParameter("isRun");
        this.permits = config.getInitParameter("permits");
        this.userConstants = config.getInitParameter("userConstants");
        this.wrongUrl = config.getInitParameter("wrongUrl");
    }
}
