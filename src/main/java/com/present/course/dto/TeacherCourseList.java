package com.present.course.dto;

import com.present.course.bean.Course;

import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 *
 * 老师课程的清单
 *
 */
public class TeacherCourseList {

    List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
