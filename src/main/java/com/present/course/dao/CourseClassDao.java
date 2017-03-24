/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.course.dao;


import com.present.course.bean.Course;
import com.present.course.bean.CourseClass;

import java.util.List;

public interface CourseClassDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param courseClass 插入的数据信息
     */
    void insert(CourseClass courseClass);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id 查询的数据信息条件
     * @return CourseClass 符合条件的数据信息
     */
    CourseClass queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param courseClass 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(CourseClass courseClass);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);


    /**
     * 查询学生已经参加的班级id通过
     *
     * @param classId   班级id
     * @return          返回课程链表
     */
    List<Course> queryCourseByClassId(String classId);
}