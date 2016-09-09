package com.globalite.dangdang.mvc.form;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.decorator.BookDecorator;
import java.util.List;

public class BooksForm {
    private String condition = null;
    private List<BookDecorator> books = null;

    public BooksForm() {
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<BookDecorator> getBooks() {
        return this.books;
    }

    public void setBooks(List<BookDecorator> books) {
        this.books = books;
    }
}
