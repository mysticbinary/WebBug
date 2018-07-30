package servlet.login;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CheckPictureCodeServlet extends HttpServlet {
    private static int WIDTH = 60;
    private static int HEIGHT = 20;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();

        //设置浏览器不要缓存此图片 setHeader setDateHeader
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        //创建内存图像并获得其图形上下文
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        //产生随机的认证码
        char[] rands = generateCheckCode();
        //产生图像
        drawBackground(graphics);
        drawRands(graphics, rands);
        //结束图像输出到客户端
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "JPEG", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        resp.setContentLength(bytes.length);
        //下面的语句也可以写成 byteArrayInputStream.writeTo(outputStream);
        outputStream.write(bytes);
        //close
        byteArrayOutputStream.close();
        outputStream.close();
        //将当前验证码存入Session中 ------- 登录时校验是否一致 不同线程之间的Session也不共享
        session.setAttribute("check_code", new String(rands));
        // 直接使用下面的代码将有问题，session对象必须在提交响应前获得
        //req.getSession().setAttribute("check_code", new String(rands));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //生成一个4字符的验证码
    private char[] generateCheckCode() {
        //定义验证码的字符表
        String chars = "0123456789abcdefghijklnmopqrstuvwxyz";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    private void drawRands(Graphics g, char[] rands) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
        //在不同的高度输出验证码的每个字符
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
        System.out.println("服务器生成的随机验证码（4位）:" + rands[0] + rands[1] +rands[2]+rands[3]);
    }

    //画背景
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //随机产生120个干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }
}
