package com.globalite.dangdang.taglib;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.taglib.LeftMenuManager;
import com.globalite.dangdang.taglib.entity.Item;
import com.globalite.dangdang.taglib.entity.Mapping;
import com.globalite.dangdang.taglib.entity.Menu;
import com.globalite.dangdang.taglib.entity.Root;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.xml.sax.InputSource;

public class LeftMenuTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    public static final String DEFAULT_PATH = "/WEB-INF/conf/leftmenu.xml";
    public static final String DTD_PATH = "/WEB-INF/conf/leftmenu.dtd";
    private String path = null;

    public LeftMenuTag() {
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int doStartTag() throws JspException {
        InputStream stream = super.pageContext.getServletContext().getResourceAsStream(this.path == null?"/WEB-INF/conf/leftmenu.xml":this.path);
        InputSource source = new InputSource(super.pageContext.getServletContext().getResourceAsStream("/WEB-INF/conf/leftmenu.dtd"));
        Menu menu = LeftMenuManager.getInstance(stream, source).getMenu();
        if(menu == null) {
            return 6;
        } else {
            List menuRoots = menu.getRoots();
            List mappings = menu.getMappings();
            if(menuRoots != null && menuRoots.size() != 0) {
                JspWriter out = super.pageContext.getOut();
                StringBuffer buffer = new StringBuffer();
                buffer.append(this.drawTitle());
                buffer.append(this.drawMenu(menuRoots, mappings));
                buffer.append(this.drawEnd());

                try {
                    out.write(buffer.toString());
                } catch (IOException var9) {
                    var9.printStackTrace();
                }

                return 6;
            } else {
                return 6;
            }
        }
    }

    private String drawTitle() {
        return "<div id=\"leftMenu\"> \r\n";
    }

    private String drawMenu(List<Root> menuRoots, List<Mapping> mappings) {
        StringBuffer buffer = new StringBuffer("");
        Mapping mapping = this.getSelectedId(mappings);

        for(int i = 0; i < menuRoots.size(); ++i) {
            Root root = (Root)menuRoots.get(i);
            if(i == 0) {
                buffer.append("\t <ul class=\"root\"> \r\n");
            } else {
                buffer.append("\t <ul class=\"root root_top\"> \r\n");
            }

            buffer.append("\t\t <li><h3>" + root.getName() + "</h3></li> \r\n");

            for(int j = 0; j < root.getItems().size(); ++j) {
                Item item = (Item)root.getItems().get(j);
                String selectedClass = "";
                if(mapping != null && item.getId().equalsIgnoreCase(mapping.getSelected())) {
                    selectedClass = "class=\"" + mapping.getClazz() + "\"";
                }

                buffer.append("\t\t <li><a href=\"" + item.getUrl() + "\" " + selectedClass + " >" + item.getContent() + "</a></li> \r\n");
            }

            buffer.append("\t </ul> \r\n");
        }

        return buffer.toString();
    }

    private Mapping getSelectedId(List<Mapping> mappings) {
        String page = ((HttpServletRequest)super.pageContext.getRequest()).getRequestURI();
        page = page.substring(page.lastIndexOf("/") + 1);
        Iterator var4 = mappings.iterator();

        while(var4.hasNext()) {
            Mapping mapping = (Mapping)var4.next();
            if(mapping.getPage().equals(page)) {
                return mapping;
            }
        }

        return null;
    }

    private String drawEnd() {
        return "</div> \r\n";
    }
}
