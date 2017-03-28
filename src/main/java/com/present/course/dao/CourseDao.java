package com.present.course.dao;


import com.present.course.bean.Course;
import org.apache.ibatis.annotations.Param;

/**
 *
 * 课程相关的dao层
 *
 */
public interface CourseDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param course 插入的数据信息
     */
    void insert(Course course);

    /**
     * 根据key查找数据库中信息
     * 
     * @param  id 查询的数据信息条件
     * @return Course 符合条件的数据信息
     */
    Course queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param course 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(Course course);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);


    /**
     * 根据课程名获取课程id
     *
     * @param   courseName
     * @return
     */
    String queryIdByCourseName(@Param("courseName")String courseName);
}