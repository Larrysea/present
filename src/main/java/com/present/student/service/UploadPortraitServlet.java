package com.present.student.service;

import com.present.common.dto.ResponseDto;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 学生上传头像的servlet
 * <p>
 * Created by Larry-sea on 2017/5/15.
 */
@WebServlet("/studentUploadPortrait")
public class UploadPortraitServlet extends HttpServlet {


    @Autowired
    StudentDao studentDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        @SuppressWarnings("deprecation")
        String path = request.getRealPath("/upload");//设置磁盘缓冲路径
        path += "portrait" + request.getParameter("studentId") + ".jpg";
        factory.setRepository(new File(path));
        factory.setSizeThreshold(1024 * 1024);//设置创建缓冲大小

        ServletFileUpload upload = new ServletFileUpload(factory);
        // upload.setSizeMax(-1);//设置上传文件限制大小,-1无上限
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> list = upload.parseRequest(request);
            String va = null;
            for (FileItem item : list) {
                //      String name = item.getFieldName();
                if (item.isFormField()) {//判断是否是文件流

                    va = item.getString("UTF-8");
                } else {
                    String value = item.getName();//会将完整路径名传过来
                    int start = value.lastIndexOf("\\");
                    String fileName = value.substring(start + 1);
                    InputStream in = item.getInputStream();
                    int index = fileName.lastIndexOf(".");
                    String realFileName = fileName.substring(0, index);
                    String type = fileName.substring(index + 1);
                    Student student = new Student();
                    student.setId(request.getParameter("studentId"));
                    student.setPortraitUrl(path);
                    studentDao.updateByKey(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseDto<String> responseDto = new ResponseDto<String>();
        responseDto.setData(path);
        resp.getWriter().print(path);

    }
}
