package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import com.globalite.dangdang.mvc.form.BooksForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BooksAction extends HttpServlet {
    private static final long serialVersionUID = 1868143932956772954L;

    public BooksAction() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        BooksForm vo = new BooksForm();
        vo.setCondition(request.getParameter("condition"));
        request.setAttribute("booksForm", vo);
        BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
        List books = dao.findBooks(vo.getCondition());
        ArrayList decorators = new ArrayList();

        for(int i = 0; i < books.size(); ++i) {
            decorators.add(new BookDecorator((Book)books.get(i)));
        }

        vo.setBooks(decorators);
        request.getRequestDispatcher("/WEB-INF/jsp/book/books.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
