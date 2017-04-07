
package com.present.student.dao;


import com.present.student.bean.ClassStudent;
import com.present.student.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassStudentDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param classStudent 插入的数据信息
     */
    void insert(ClassStudent classStudent);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id 查询的数据信息条件
     * @return ClassStudent 符合条件的数据信息
     */
    ClassStudent queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param classStudent 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(ClassStudent classStudent);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);

    /**
     * 通过班级id获取某个班级的学生信息
     *
     * @param classId
     * @return
     */
    List<Student> queryStudentIdOfOneClass(@Param("classId")String classId);
}