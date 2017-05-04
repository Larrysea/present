package com.present.sign.dao;

import com.present.sign.dto.StudentSignInfoOfTermDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Larry-sea on 2017/3/23.
 *
 * 查看某个老师的某个课程的某个班级的学生考勤信息的dao
 *
 */
public interface CourseSignInfoInTermDao {

    /**
     * 获取学生一学期的签到信息
     *
     * @param teacherId   老师id
     * @param courseId    课程id
     * @param classId     班级id
     * @return
     */
    List<StudentSignInfoOfTermDto> getCourseSignInfoInTerm(@Param("teacherId")String teacherId, @Param("courseId")String courseId, @Param("classId") String classId);
}
