package com.present.sign.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.CourseSign;
import com.present.sign.bean.SignStartWithClass;
import com.present.sign.bean.StudentSign;
import com.present.sign.dao.CourseSignDao;
import com.present.sign.dao.SignStartWithClassDao;
import com.present.sign.dao.StudentSignDao;
import com.present.student.baen.Student;
import com.present.student.dao.ClassStudentDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 已经确定是那个课程了，现在开始选择班级进行签到
 */
public class SelectClassToSign extends BaseService {


    @Autowired
    SignStartWithClassDao signStartWithClassDao;

    @Autowired
    ClassStudentDao classStudentDao;

    @Autowired
    StudentSignDao studentSignDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("course_sign_id"), params.getString("classArray"));

        return selectClassesToSign(params);
    }


    /**
     * 在确定某个课程的情况下选择班级进行签到
     *
     * @param params 输入参数r
     * @return 返回responseDto
     */
    public ResponseDto selectClassesToSign(JSONObject params) {
        JSONArray classArray = null;
        SignStartWithClass signStartWithClass;
        if (null != params.getJSONArray("classArray")) {
            classArray = params.getJSONArray("classArray");
            for (int position = 0; position < classArray.size(); position++) {
                signStartWithClass = (SignStartWithClass) classArray.get(position);
                signStartWithClassDao.insert(signStartWithClass);
            }
        }
        return new ResponseDto();
    }


    /**
     * 当选择班级以后需要初始化每个学生的签到信息，
     * 因此通过班级查找到两个班级的学生，
     * 然后插入到student_sign_in 表中其中之初始化三个字段，分别是coursesign_id和student_id和 sign_state
     * sign_state 默认状态为3 缺勤状态，这时学生签到以后会修改这个sign_state 为签到状态
     * 或者是老师修改为病假状态。
     *
     * @param classArray   班级数组
     * @param courseSignId 课程签到id
     */
    public void initStudentSignInfo(JSONArray classArray, String courseSignId) {
        if (null == classArray) {
            throw new IllegalArgumentException("classArray cant empty ");
        }
        if (null == courseSignId) {
            throw new IllegalArgumentException("courseSignId is empty ");
        }
        String classId;
        List<Student> studentList = null;
        StudentSign studentSign = new StudentSign();
        //查找出所有班级信息
        for (int classPosition = 0; classPosition < classArray.size(); classPosition++) {
            classId = (String) classArray.get(classPosition);
            if (studentList != null) {
                studentList.clear();
            }
            studentList = classStudentDao.queryStudentIdOfOneClass(classId);
            if (studentList != null) {
                //插入每个学生的信息到表中
                for (int studentPosition = 0; studentPosition < studentList.size(); studentPosition++) {
                    studentSign.setCourseSignId(courseSignId);
                    studentSign.setStudentSignId(studentList.get(studentPosition).getId());
                    studentSign.setSignState(Constants.ABSENCE);
                    studentSignDao.insert(studentSign);
                }
            } else {
                throw new IllegalArgumentException("student list is empty");
            }
        }


    }

}
