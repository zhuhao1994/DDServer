package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Bookcomment;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.CommentsResponseParams;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class CommentsParamsBuilder implements Builder {
    public CommentsParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        Book books = (Book)entity;
        ArrayList params = new ArrayList();

        CommentsResponseParams param;
        for(Iterator var6 = books.getBookcomments().iterator(); var6.hasNext(); params.add(param)) {
            Bookcomment comment = (Bookcomment)var6.next();
            param = new CommentsResponseParams();
            param.setStar(String.valueOf(comment.getStar()));
            param.setCustomerName(comment.getCustomer().getName());
            param.setContent(comment.getContent());
            if(comment.getContentdate() != null) {
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                param.setContentDate(formater.format(comment.getContentdate()));
            } else {
                param.setContentDate("");
            }
        }

        return params;
    }
}

