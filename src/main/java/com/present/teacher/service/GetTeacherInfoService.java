package com.present.teacher.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.login.bean.Teacher;
import com.present.login.dao.TeacherDao;
import com.present.login.dto.TeacherLoginSuccessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/19.
 * <p>
 * 老师获取自身信息服务
 */
@Service("getTeacherInfo")
public class GetTeacherInfoService extends BaseService<TeacherLoginSuccessDto> {

    @Autowired
    TeacherDao teacherDao;

    @Override
    public ResponseDto<TeacherLoginSuccessDto> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacherId");
        ResponseDto<TeacherLoginSuccessDto> teacherLoginSuccessDto = new ResponseDto<TeacherLoginSuccessDto>();
        Teacher teacher = teacherDao.queryByKey(params.getString("teacherId"));
        teacherLoginSuccessDto.setData(converTeacherToLoginDto(teacher));
        return teacherLoginSuccessDto;
    }


    /**
     * 初始化登录成功的返回的数据dto
     *
     * @return
     */
    public static TeacherLoginSuccessDto converTeacherToLoginDto(final Teacher teacher) {
        if (teacher != null) {
            TeacherLoginSuccessDto teacherLoginSuccessDto = new TeacherLoginSuccessDto();
            teacherLoginSuccessDto.setName(teacher.getName());
            teacherLoginSuccessDto.setMail(teacher.getMail());
            teacherLoginSuccessDto.setPhone(teacher.getPhone());
            teacherLoginSuccessDto.setSchoolId(teacher.getSchoolId());
            teacherLoginSuccessDto.setUserId(teacher.getId());
            return teacherLoginSuccessDto;
        } else {
            throw new IllegalArgumentException("param cant empty");
        }
    }

}
