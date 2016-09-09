package com.globalite.dangdang.mvc.action;

/**
 * Created by zhu on 2016/9/5.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CodeAction extends HttpServlet {
    private static final long serialVersionUID = -1190717048753037659L;
    private Font[] codeFont = new Font[]{new Font("Times New Roman", 0, 18), new Font("Times New Roman", 0, 18), new Font("Times New Roman", 0, 18), new Font("Times New Roman", 0, 18)};
    private Color[] color;
    private String codeNumbers;
    private final String IMAGE_CHAR;
    private final Integer IMAGE_WIDTH;
    private final Integer IMAGE_HEIGHT;

    public CodeAction() {
        this.color = new Color[]{Color.BLACK, Color.RED, Color.DARK_GRAY, Color.BLUE};
        this.codeNumbers = "";
        this.IMAGE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        this.IMAGE_WIDTH = Integer.valueOf(80);
        this.IMAGE_HEIGHT = Integer.valueOf(20);
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        BufferedImage image = new BufferedImage(this.IMAGE_WIDTH.intValue(), this.IMAGE_HEIGHT.intValue(), 1);
        Graphics g = image.getGraphics();
        g.setColor(this.getRandColor(200, 250));
        g.fillRect(0, 0, this.IMAGE_WIDTH.intValue(), this.IMAGE_HEIGHT.intValue());

        for(int session = 0; session < 4; ++session) {
            this.drawCode(g, session);
        }

        this.drawNoise(g, 12);
        HttpSession var7 = request.getSession(true);
        var7.removeAttribute("com.globalite.dangdang.constants.checknumber");
        var7.setAttribute("com.globalite.dangdang.constants.checknumber", this.codeNumbers);
        this.codeNumbers = "";
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "GIF", sos);
        sos.close();
    }

    public void drawCode(Graphics graphics, int i) {
        Random random = new Random();
        Integer j = Integer.valueOf(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length()));
        String number = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".substring(j.intValue(), j.intValue() + 1);
        graphics.setFont(this.codeFont[i]);
        graphics.setColor(this.color[i]);
        graphics.drawString(number, 6 + i * 13, 16);
        this.codeNumbers = this.codeNumbers + number;
    }

    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void drawNoise(Graphics graphics, int lineNumber) {
        graphics.setColor(this.getRandColor(160, 200));

        for(int i = 0; i < lineNumber; ++i) {
            int pointX1 = 1 + (int)(Math.random() * 80.0D);
            int pointY1 = 1 + (int)(Math.random() * 20.0D);
            int pointX2 = 1 + (int)(Math.random() * 80.0D);
            int pointY2 = 1 + (int)(Math.random() * 20.0D);
            graphics.drawLine(pointX1, pointY1, pointX2, pointY2);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void init() throws ServletException {
    }
}

