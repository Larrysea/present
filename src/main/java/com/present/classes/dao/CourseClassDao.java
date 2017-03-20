package com.present.classes.dao;

import com.present.classes.bean.Classes;

import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 *
 * 课程老师dao层
 *
 */
public interface CourseClassDao {

    /**
     *
     * 通过老师的id和课程获取下面所对应的班级信息
     * @param teacherId
     * @param courseId
     * @return   返回班级list
     */
   List<Classes>    queryClassesByteacherAndCourse(String teacherId,String courseId);






}
