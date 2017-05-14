package com.present.student.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.service.ReadExcel;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/23.
 * <p>
 * 学生上传头像接口
 */

@Service("studentUploadPortrait")
public class StudentUpaloadPortraitService extends BaseService<String> {
    private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    @Autowired
    StudentDao studentDao;


    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        @SuppressWarnings("deprecation")
        String path = request.getRealPath("/upload");//设置磁盘缓冲路径
        path += "portrait" + params.getString("studentId") + ".jpg";
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
                    student.setId(params.getString("studentId"));
                    student.setPortraitUrl(path);
                    studentDao.updateByKey(student);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return super.process(params, request, response);
    }
}


