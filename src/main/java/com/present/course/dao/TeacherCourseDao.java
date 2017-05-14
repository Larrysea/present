
package com.present.course.dao;


import com.present.course.bean.Course;
import com.present.course.bean.TeacherCourse;
import com.present.course.dto.CourseAndTeacherDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老师课程的操作数据dao
 */
public interface TeacherCourseDao {
    /**
     * 将数据插入数据库表中
     *
     * @param teacherCourse 插入的数据信息
     */
    void insert(TeacherCourse teacherCourse);

    /**
     * 根据key查找数据库中信息
     *
     * @param id 查询的数据信息条件
     * @return TeacherCourse 符合条件的数据信息
     */
    TeacherCourse queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     *
     * @param teacherCourse 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(TeacherCourse teacherCourse);

    /**
     * 根据key删除数据库中信息
     *
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);

    /**
     * 查询根据老师id获取老师的课程列表
     *
     * @param teacherId
     * @return
     */
    List<Course> queryCourseByTeacherId(@Param("teacherId") String teacherId);


    /**
     * 获取老师和课程信息
     *
     * @param courseId
     * @return
     */
    CourseAndTeacherDto getTeacherAndCourseInfo(@Param("courseId") String courseId);


}