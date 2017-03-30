package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.StudentSign;
import com.present.sign.dao.StudentSignDao;
import com.present.sign.dto.StudentCourseSignDto;
import com.present.sign.dto.StudentSignInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * <p>
 * 通过课程签到id和班级id获取那次签到的每个人的签到信息
 */
@Service("getSignInfoByCourseSignIdAndClassId")
public class GetTheSignInfoByCourseSignId extends BaseService<List<StudentCourseSignDto>> {


    @Autowired
    StudentSignDao studentSignDao;



    @Override
    public ResponseDto<List<StudentCourseSignDto>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignId", "classId");
        List<StudentCourseSignDto> studentSignList = studentSignDao.getCourseSignInforList(params.getString("courseSignId"), params.getString("classId"));
        ResponseDto<List<StudentCourseSignDto>> studentSignResponseDto = new ResponseDto<List<StudentCourseSignDto>>();
        studentSignResponseDto.setData(studentSignList);
        return studentSignResponseDto;

    }


    /**
     * 将studentSign转换为StudentCourseSignDto
     *
     * @param studentSignInfoDto
     * @return
     */
    public StudentCourseSignDto convertStudentSignToStudentCourseSignDto(final StudentSignInfoDto studentSignInfoDto) {
        if (studentSignInfoDto != null) {
            throw new IllegalArgumentException("param studentSign cant empty");
        }
        final StudentCourseSignDto studentCourseSignDto = new StudentCourseSignDto();
        studentCourseSignDto.setName(studentSignInfoDto.getName());
        studentCourseSignDto.setSignState(studentSignInfoDto.getSignState());
        studentCourseSignDto.setStudentNumber(studentSignInfoDto.getStudentNumber());
        return studentCourseSignDto;
    }


}
