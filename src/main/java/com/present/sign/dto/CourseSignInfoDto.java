package com.present.sign.dto;

import com.present.sign.bean.CourseSign;
import com.present.sign.bean.StudentSign;

import java.util.Date;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/21.
 *
 *
 *
 * 老师获取某门课程的签到dto
 */
public class CourseSignInfoDto {

    /*
    * 总的签到次数
    * */
    int signCount;
    /*
    * 每个学生的签到记录
    * */
    List<StudentSign> studentSignList;

    /**
     * 签到课程信息
     *
     */
    CourseSign courseSign;


    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
    }

    public List<StudentSign> getStudentSignList() {
        return studentSignList;
    }

    public void setStudentSignList(List<StudentSign> studentSignList) {
        this.studentSignList = studentSignList;
    }

    public CourseSign getCourseSign() {
        return courseSign;
    }

    public void setCourseSign(CourseSign courseSign) {
        this.courseSign = courseSign;
    }
}
