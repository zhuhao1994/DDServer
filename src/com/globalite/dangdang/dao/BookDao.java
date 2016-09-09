package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import java.util.List;

public interface BookDao {
    List<?> findBooks(String var1);

    Book findFullBook(Long var1, boolean var2);
}