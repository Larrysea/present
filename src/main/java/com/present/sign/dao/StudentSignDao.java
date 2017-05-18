
package com.present.sign.dao;


import com.present.sign.bean.StudentSign;
import com.present.sign.dto.CourseSignInfoDto;
import com.present.sign.dto.StudentCourseSignDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StudentSignDao {
    /**
     * 将数据插入数据库表中
     *
     * @param studentSign 插入的数据信息
     */
    void insert(StudentSign studentSign);

    /**
     * 根据key查找数据库中信息
     *
     * @param id 查询的数据信息条件
     * @return StudentSign 符合条件的数据信息
     */
    StudentSign queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     *
     * @param studentSign 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(StudentSign studentSign);

    /**
     * 根据key删除数据库中信息
     *
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);

    /**
     * 根据课程签到id获取所有学生某一堂课的签到信息详情
     * 也就是查看某一次课程签到的所有学生考勤信息
     *
     * @param courseSignId
     * @return 返回学生签到情况列表
     */
    List<StudentCourseSignDto> getCourseSignInforList(@Param("courseSignId") String courseSignId, @Param("classId") String classId);


    /**
     * 修改学生的签到状态
     *
     * @param courseSignId 课程签到id
     * @param studentId    学生id
     * @param signTime     签到时间
     * @param signState    签到状态
     */
    void changeStudentSignState(@Param("courseSignId") String courseSignId, @Param("studentId") String studentId, @Param("signTime") Date signTime, @Param("signState") String signState);


    /**
     * 查询学生的某个课程所有签到信息dto
     *
     * @param courseId
     * @param studentId
     * @return
     */
    List<CourseSignInfoDto> queryCourseSignInfoDto(@Param("courseId") String courseId, @Param("studentId") String studentId);


    /**
     * 查询
     *
     * @param courseSignId
     * @return
     */
    List<StudentCourseSignDto> queryStudentSignDtoByCourseSignId(@Param("courseSignId") String courseSignId);


    /**
     * 获取某次签到中缺勤的学生记录
     *
     * @param courseSignId
     * @return
     */
    List<StudentCourseSignDto> getAbsenceStudentList(@Param("courseSignId") String courseSignId);

}

