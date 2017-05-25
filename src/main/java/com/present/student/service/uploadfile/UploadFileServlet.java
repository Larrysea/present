package com.present.student.service.uploadfile;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UploadFileServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String portraitSavePath = request.getSession().getServletContext().getRealPath("\\temp");

        File file = new File(portraitSavePath);
        System.out.println("文件路径" + file.exists());
        // 创建文件项目工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
        String temp = System.getProperty("java.io.tmpdir");
        // 设置缓冲区大小为 5M
        factory.setSizeThreshold(1024 * 1024 * 5);
        // 设置临时文件夹为temp
        factory.setRepository(new File(temp));
        // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        //文件保存路径
        String fileName = null;


        // 解析结果放在List中
        try {
            List<FileItem> list = servletFileUpload.parseRequest(request);

            for (FileItem item : list) {
                String name = item.getFieldName();
                InputStream is = item.getInputStream();

                if (name.contains("content")) {
                    System.out.println(inputStream2String(is));
                } else if (name.contains("file")) {
                    try {
                        fileName = item.getName();
                        System.out.println("fileItemName" + fileName);
                        String fileSavePath = "C:\\Users\\Larry-sea\\OneDrive\\code\\gitpath\\present\\src\\main\\webapp\\resource\\head\\" + fileName;
                        inputStream2File(is, fileSavePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("failure");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "200");
        jsonObject.put("data", "resource/head/" + fileName);
        jsonObject.put("msg", "成功");
        out.print(jsonObject.toJSONString());
        out.flush();
        out.close();
    }

    // 流转化成字符串
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    // 流转化成文件
    public static void inputStream2File(InputStream is, String savePath)
            throws Exception {
        System.out.println("fileSavePath:" + savePath);
        File file = new File(savePath);
        if (file.exists()) {
            file.delete();
        }
        boolean result = file.createNewFile();
        if (result) {
            InputStream inputSteam = is;
            BufferedInputStream fis = new BufferedInputStream(inputSteam);
            FileOutputStream fos = new FileOutputStream(file);
            int f;
            while ((f = fis.read()) != -1) {
                fos.write(f);
            }
            fos.flush();
            fos.close();
            fis.close();
            inputSteam.close();
        }


    }

}
