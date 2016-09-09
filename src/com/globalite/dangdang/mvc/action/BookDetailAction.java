package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookDetailAction extends HttpServlet {
    private static final long serialVersionUID = -3727023049059564676L;

    public BookDetailAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        if(id == null) {
            response.sendRedirect("MainBooksServlet");
        } else {
            BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
            Book book = dao.findFullBook(Long.valueOf(id), false);
            request.setAttribute("book", new BookDecorator(book));
            request.getRequestDispatcher("/WEB-INF/jsp/book/details.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

