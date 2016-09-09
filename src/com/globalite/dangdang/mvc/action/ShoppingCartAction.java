package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.mvc.form.ShoppingCartForm;
import com.globalite.dangdang.service.CartService;
import com.globalite.dangdang.service.CartServiceAdapter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartAction extends HttpServlet {
    private static final long serialVersionUID = -8393091287785762463L;

    public ShoppingCartAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        ShoppingCartForm vo = this.createForm(request);
        String regName = ((Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername")).getRegname();
        if(regName == null) {
            request.getRequestDispatcher("/index.jhtml").forward(request, response);
        } else {
            CartServiceAdapter service = new CartServiceAdapter(request, regName);
            if(!this.checkCart(vo).booleanValue()) {
                request.getRequestDispatcher("/index.jhtml").forward(request, response);
            } else if(vo.getOp() == null) {
                request.setAttribute("com.globalite.dangdang.constants.shoppingcart", service.getCart());
                request.getRequestDispatcher("WEB-INF/jsp/cart/cart.jsp").forward(request, response);
            } else {
                Book book = ((BookDao)SpringManager.getManager().getBean("bookDao")).findFullBook(Long.valueOf(vo.getId()), true);
                if(book == null) {
                    request.getRequestDispatcher("/index.jhtml").forward(request, response);
                } else {
                    this.performShoppingCart(vo, service, book);
                    request.setAttribute("com.globalite.dangdang.constants.shoppingcart", service.getCart());
                    request.getRequestDispatcher("WEB-INF/jsp/cart/cart.jsp").forward(request, response);
                }
            }
        }
    }

    public Boolean checkCart(ShoppingCartForm vo) {
        boolean ret = true;
        if("add".equals(vo.getOp()) || "del".equals(vo.getOp()) || "edit".equals(vo.getOp())) {
            if(vo.getId() == null) {
                return Boolean.valueOf(false);
            }

            try {
                Long.valueOf(vo.getId());
            } catch (NumberFormatException var6) {
                return Boolean.valueOf(false);
            }
        }

        if("add".equals(vo.getOp()) && vo.getNumber() != null) {
            try {
                Integer.valueOf(vo.getNumber());
            } catch (NumberFormatException var5) {
                return Boolean.valueOf(false);
            }
        }

        if("edit".equals(vo.getOp()) && vo.getNumber() == null) {
            return Boolean.valueOf(false);
        } else {
            if("edit".equals(vo.getOp()) && vo.getNumber() != null) {
                try {
                    Integer.valueOf(vo.getNumber());
                } catch (NumberFormatException var4) {
                    return Boolean.valueOf(false);
                }
            }

            return Boolean.valueOf(ret);
        }
    }

    private ShoppingCartForm createForm(HttpServletRequest request) {
        ShoppingCartForm vo = new ShoppingCartForm();
        request.setAttribute("shoppingCartForm", vo);
        vo.setId(request.getParameter("id"));
        vo.setOp(request.getParameter("op"));
        vo.setNumber(request.getParameter("number"));
        return vo;
    }

    private void performShoppingCart(ShoppingCartForm vo, CartService service, Book book) {
        if("add".equals(vo.getOp())) {
            service.addBook(book, vo.getNumber());
        } else if("del".equals(vo.getOp())) {
            service.removeBook(book);
        } else if("edit".equals(vo.getOp())) {
            service.editBook(book, vo.getNumber());
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

