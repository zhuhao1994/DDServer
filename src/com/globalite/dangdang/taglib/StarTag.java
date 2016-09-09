package com.globalite.dangdang.taglib;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Bookcomment;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class StarTag extends TagSupport {
    private List<Bookcomment> comments = null;
    private String redStar = null;
    private String grayStar = null;
    private static final long serialVersionUID = 6101191474432170734L;

    public StarTag() {
    }

    public List<Bookcomment> getComments() {
        return this.comments;
    }

    public String getRedStar() {
        return this.redStar;
    }

    public void setRedStar(String redStar) {
        this.redStar = redStar;
    }

    public String getGrayStar() {
        return this.grayStar;
    }

    public void setGrayStar(String grayStar) {
        this.grayStar = grayStar;
    }

    public void setComments(List<Bookcomment> comments) {
        this.comments = comments;
    }

    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    public int doStartTag() throws JspException {
        if(this.getComments() != null && this.getComments().size() > 0) {
            JspWriter out = super.pageContext.getOut();

            try {
                int e = this.getAvgStart();
                out.println("<span>" + this.getAvgStart() + "星</span>");
                out.println("<s>");

                int i;
                for(i = 0; i < e; ++i) {
                    out.println("<img src=\"" + this.redStar + "\" />");
                }

                if(e < 5) {
                    for(i = 0; i < 5 - e; ++i) {
                        out.println("<img src=\"" + this.grayStar + "\" />");
                    }
                }

                out.println("</s>");
            } catch (IOException var4) {
                var4.printStackTrace();
            }

            return 6;
        } else {
            return 6;
        }
    }

    private int getAvgStart() {
        int sum = 0;

        Bookcomment comment;
        for(Iterator var3 = this.comments.iterator(); var3.hasNext(); sum += comment.getStar().intValue()) {
            comment = (Bookcomment)var3.next();
        }

        return sum == 0?0:sum / this.comments.size();
    }
}
