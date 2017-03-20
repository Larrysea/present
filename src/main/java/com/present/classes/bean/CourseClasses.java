package com.present.classes.bean;

import com.present.course.bean.Course;

import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 *
 * 课程班级下面bean
 *
 */
public class CourseClasses {
    List<Course>  classesList;

    public List<Course> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Course> classesList) {
        this.classesList = classesList;
    }
}
