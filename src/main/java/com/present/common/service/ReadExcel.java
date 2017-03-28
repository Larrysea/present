package com.present.common.service;

import com.present.common.util.POIUtil;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/21.
 * <p>
 * 读取excel
 */
@Service("readExcel")
public class ReadExcel {


    private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    @Autowired
    StudentDao studentDao;

    /**
     * 读取excel文件中的用户信息，保存在数据库中
     *
     * @param excelFile
     */
    @RequestMapping("/readExcel")
    public void readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest req, HttpServletResponse resp) {
        logger.info("在readExcel 方法中");
        List<Student> allUsers = new ArrayList<Student>();
        try {
            List<String[]> userList = POIUtil.readExcel(excelFile);
            for (int i = 0; i < userList.size(); i++) {
                String[] studentArray = userList.get(i);
                Student student = new Student();
                student.setPassword(studentArray[0]);
                student.setName(studentArray[1]);
                allUsers.add(student);
            }
        } catch (IOException e) {
            logger.info("读取excel文件失败", e);
        }
        studentDao.insertStudentList(allUsers);
    }


}
