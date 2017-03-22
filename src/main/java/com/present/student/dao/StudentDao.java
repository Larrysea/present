/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.student.dao;


import com.present.student.baen.Student;

import java.util.List;

public interface StudentDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param student 插入的数据信息
     */
    void insert(Student student);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id 查询的数据信息条件
     * @return Student 符合条件的数据信息
     */
    Student queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param student 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(Student student);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);


    /**
     * 检查学生学号是否存在
     *
     * @param studentNumber
     * @return  返回查询到的id
     */
    String isVaildUser(String studentNumber,String schoolId);


    /**
     * 学生用户登录
     *
     * @param schoolId         学校id
     * @param studentNumber    手机号
     * @param password         密码
     * @return
     */
    Student studentLogin(String schoolId,String  studentNumber,String password);




    /**
     *
     * 批量插入用户列表
     *
     * @param studentList
     */
    void insertStudentList(List<Student> studentList);

}