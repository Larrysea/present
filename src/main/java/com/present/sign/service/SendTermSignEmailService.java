package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.ExcelUtil;
import com.present.common.util.SendMail;
import com.present.sign.dao.CourseSignInfoInTermDao;
import com.present.sign.dto.StudentSignInfoOfTermDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/5/25.
 *
 *
 * 发送学期汇总签到邮件接口
 *
 */
//todo 完成这个接口，将list转换为array之后处理
@Service("sendSignMailService")
public class SendTermSignEmailService  extends BaseService<String>{



    /*
    *
    * 获取课程签到信息的dto
    * */
    @Autowired
    CourseSignInfoInTermDao getCourseSignInfoInTerm;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params,"teacherId","courseId","classId");
        ExcelUtil.exportExcel();
        SendMail sendMail=new SendMail();
        List<StudentSignInfoOfTermDto> studentSignInfoDtos = getCourseSignInfoInTerm.getCourseSignInfoInTerm(params.getString("teacherId"), params.getString("courseId"), params.getString("classId"));
        transStudentSignListToArray();
        sendMail.sendAttachment(Constants.sendTearmSignExcetl,Constants.emailContent,);
        return super.process(params, request, response);
    }

    public String[][] transStudentSignListToArray(List<StudentSignInfoOfTermDto> studentSignInfoOfTermDtoList)
    {
        String[][]


    }

}
