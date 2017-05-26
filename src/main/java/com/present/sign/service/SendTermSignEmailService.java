package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.classes.dao.ClassesDao;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.ExcelUtil;
import com.present.common.util.MessageUtil;
import com.present.common.util.SendMail;
import com.present.course.bean.Course;
import com.present.course.dao.CourseDao;
import com.present.sign.dao.CourseSignInfoInTermDao;
import com.present.sign.dto.StudentSignInfoOfTermDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Larry-sea on 2017/5/25.
 * <p>
 * <p>
 * 发送学期汇总签到邮件接口
 */
@Service("sendSignMailService")
public class SendTermSignEmailService extends BaseService<String> {


    /*
    *
    * 获取课程签到信息的dto
    * */
    @Autowired
    CourseSignInfoInTermDao getCourseSignInfoInTerm;

    @Autowired
    ClassesDao classesDao;

    @Autowired
    CourseDao courseDao;


    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacherId", "courseId", "classId", "mailAddress");
        SendMail sendMail = new SendMail();
        Classes classes = classesDao.queryByKey(params.getString("classId"));
        Course course = courseDao.queryByKey(params.getString("courseId"));
        String[] header = new String[3];
        String fileSavePath = null;
        if (classes != null && course != null) {

            List<StudentSignInfoOfTermDto> studentSignInfoDtos = getCourseSignInfoInTerm.getCourseSignInfoInTerm(params.getString("teacherId"), params.getString("courseId"), params.getString("classId"));
            //判断签到的数据是否为空，如果为空的则不会发送邮件并且给出提示
            if (studentSignInfoDtos != null && studentSignInfoDtos.size() > 0) {
                header[0] = course.getCourseName();
                header[1] = "签到总数";
                header[2] = getSignCount(studentSignInfoDtos.get(0));
                fileSavePath = request.getServletContext().getContextPath();
                ExcelUtil.exportExcel(transStudentSignListToArray(studentSignInfoDtos),
                        classes.getClassName(), classes.getClassName(), header, fileSavePath);
            } else {
                throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("sign.signinfo.notexist"));
            }


        }
        try {
            sendMail.sendAttachment(Constants.sendTearmSignExcetl, Constants.emailContent,
                    fileSavePath, Constants.fromMailAddress,
                    Constants.smtpAuthPwd, params.getString("mailAddress"), Constants.smtpServer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseDto<String>();
    }

    public String[][] transStudentSignListToArray(final List<StudentSignInfoOfTermDto> studentSignInfoOfTermDtoList) {
        String[][] studentSignInfoArray = new String[100][];
        StudentSignInfoOfTermDto studentSignInfoOfTermDto;
        for (int position = 0; position < studentSignInfoOfTermDtoList.size(); position++) {
            studentSignInfoOfTermDto = studentSignInfoOfTermDtoList.get(position);
            if (studentSignInfoOfTermDto != null) {
                studentSignInfoArray[position][0] = studentSignInfoOfTermDto.getStudentNumber();
                studentSignInfoArray[position][1] = studentSignInfoOfTermDto.getName();
                studentSignInfoArray[position][2] = studentSignInfoOfTermDto.getSign();
                studentSignInfoArray[position][3] = studentSignInfoOfTermDto.getSickLeave();
                studentSignInfoArray[position][4] = studentSignInfoOfTermDto.getAbsence();
            }
        }
        return studentSignInfoArray;
    }

    /**
     * 获取老师发起签到的总次数
     *
     * @param studentSignInfoOfTermDto
     * @return 返回签到总次数
     */
    public String getSignCount(StudentSignInfoOfTermDto studentSignInfoOfTermDto) {

        Integer sign = 0;
        Integer sickLeave = 0;
        Integer absence = 0;
        try {
            sign = Integer.valueOf(studentSignInfoOfTermDto.getSign());
            sickLeave = Integer.valueOf(studentSignInfoOfTermDto.getSickLeave());
            absence = Integer.valueOf(studentSignInfoOfTermDto.getAbsence());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return String.valueOf(sign + sickLeave + absence);

    }


}
