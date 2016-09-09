package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.Bookcomment;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.BooksResponseParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BooksParamsBuilder implements Builder {
    public BooksParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        List books = (List)entity;
        ArrayList params = new ArrayList();
        Iterator var6 = books.iterator();

        while(var6.hasNext()) {
            Book book = (Book)var6.next();
            BookDecorator decorator = new BookDecorator(book);
            BooksResponseParams param = new BooksResponseParams();
            param.setBookId(String.valueOf(decorator.getOwn().getId()));
            param.setBookName(decorator.getOwn().getName());
            param.setAuthor(decorator.getOwn().getAuthor());
            param.setPrice(decorator.getPrice());
            param.setImageName(decorator.getOwn().getImage().getName());
            param.setImagePath(decorator.getOwn().getImage().getPath());
            param.setStockstatus(decorator.getStock());
            param.setPublisher(decorator.getOwn().getPublisher());
            param.setIntroduction(this.toMobileString(decorator.getOwn().getIntroduction()));
            param.setCatalog(this.toMobileString(decorator.getOwn().getCatalog()));
            param.setSalesVolume(String.valueOf(decorator.getOwn().getSalesvolume()));
            param.setCommentnum(String.valueOf(decorator.getOwn().getBookcomments().size()));
            param.setStarnum(String.valueOf(this.getStarNum(decorator.getOwn().getBookcomments())));
            params.add(param);
        }

        return params;
    }

    private String toMobileString(String data) {
        return data == null?null:data.replaceAll("[<br/>]", "").replaceAll("[<p>]", "").replaceAll("[</p>]", "");
    }

    private int getStarNum(List<Bookcomment> comments) {
        if(comments == null) {
            return 0;
        } else {
            int sum = 0;

            Bookcomment comment;
            for(Iterator var4 = comments.iterator(); var4.hasNext(); sum += comment.getStar().intValue()) {
                comment = (Bookcomment)var4.next();
            }

            return sum == 0?0:sum / comments.size();
        }
    }
}
