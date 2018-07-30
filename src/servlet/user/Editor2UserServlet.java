package servlet.user;

import dao.UersDao;
import entity.Tempid;
import util.Tool;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;


public class Editor2UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        // 创建会话Session
        HttpSession session = request.getSession();
        Tempid tempid=(Tempid)session.getAttribute("tempid");
        String currentid =tempid.getId();
        System.out.println("currentid:"+currentid);

        String imagename = saveImage(request);
        System.out.println("image name:" + imagename);

        UersDao userdao = new UersDao();
        int currentid2 = Integer.parseInt(currentid);
        Boolean uploadimage = userdao.uploadimage(currentid2, imagename);

        PrintWriter writer = response.getWriter();
        if(uploadimage== true){
            writer.write(
                    "<h2>ucceed Editor</h2>"
                            + "恭喜，用户信息修改成功！ 2秒后跳转登录页！"
            );
            //等待2秒 跳转登录页
            response.setHeader("refresh", "2;url=/UserManageServlet");
        }else {
            writer.write(
                    "<h2>Error</h2>"
                            + "用户信息修改失败！"
            );
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected String saveImage(HttpServletRequest request) throws IOException {
        //从request当中获取流信息
        InputStream fileSource = request.getInputStream();

        String tempFileName = "d:/JavaEEuploadtempFile";
        //tempFile指向临时文件
        File tempFile = new File(tempFileName);
        //outputStram文件输出流指向这个临时文件
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        byte b[] = new byte[1024];
        int n;
        while ((n = fileSource.read(b)) != -1) {
            outputStream.write(b, 0, n);
        }
        //关闭输出流、输入流
        outputStream.close();
        fileSource.close();

        //获取上传文件的名称
        RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
        randomFile.readLine();
        String str = randomFile.readLine();
//        int beginIndex = str.lastIndexOf("\\") + 1;
//        int endIndex = str.lastIndexOf("\"");
//        String filename = str.substring(beginIndex, endIndex);
        Tool tool = new Tool();
        String filename = tool.getRandomReqNo() + ".png";

        System.out.println("filename:" + filename);

        //重新定位文件指针到文件头
        randomFile.seek(0);
        long startPosition = 0;
        int i = 1;
        //获取文件内容 开始位置
        while ((n = randomFile.readByte()) != -1 && i <= 4) {
            if (n == '\n') {
                startPosition = randomFile.getFilePointer();
                i++;
            }
        }
        startPosition = randomFile.getFilePointer() - 1;
        //获取文件内容 结束位置
        randomFile.seek(randomFile.length());
        long endPosition = randomFile.getFilePointer();
        int j = 1;
        while (endPosition >= 0 && j <= 2) {
            endPosition--;
            randomFile.seek(endPosition);
            if (randomFile.readByte() == '\n') {
                j++;
            }
        }
        endPosition = endPosition - 1;

        //设置保存上传文件的路径
        String realPath = getServletContext().getRealPath("/") + "image";
        File fileupload = new File(realPath);
        if (!fileupload.exists()) {
            fileupload.mkdir();
        }
        File saveFile = new File(realPath, filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
        //从临时文件当中读取文件内容（根据起止位置获取）
        randomFile.seek(startPosition);
        while (startPosition < endPosition) {
            randomAccessFile.write(randomFile.readByte());
            startPosition = randomFile.getFilePointer();
        }
        //关闭输入输出流、删除临时文件
        randomAccessFile.close();
        randomFile.close();
        tempFile.delete();

        System.out.println("image上传成功");
        return "/image/" + filename;
    }

}
