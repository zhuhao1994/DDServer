package com.globalite.dangdang.common;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public final class WebUtils {
    private WebUtils() {
    }

    public static void showErrorMessage(HttpServletResponse response, String msg, String url) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<script type=\"text/javascript\">");
        pw.println("alert(\"" + msg + "\");");
        if(url != null) {
            pw.println("location.href = \"" + url + "\"");
        }

        pw.println("</script>");
        pw.close();
    }

    public static void showErrorMessage(HttpServletResponse response, String msg) throws IOException {
        showErrorMessage(response, msg, (String)null);
    }
}
