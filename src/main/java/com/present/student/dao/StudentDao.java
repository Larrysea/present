package com.present.student.dao;

import com.present.student.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
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
     * @return 返回查询到的id
     */
    String isValidUser(@Param("studentNumber") String studentNumber, @Param("password") String password, @Param("schoolId") String schoolId);


    /**
     * 学生用户登录
     *
     * @param schoolId      学校id
     * @param studentNumber 手机号
     * @param password      密码
     * @return
     */
    Student studentLogin(@Param("schoolId") String schoolId, @Param("studentNumber") String studentNumber, @Param("password") String password);


    /**
     * 批量插入用户列表
     *
     * @param studentList
     */
    void insertStudentList(@Param("studentList") List<Student> studentList);


    /**
     *
     * 完善学生个人信息接口
     * @param studentId     学生id
     * @param password      密码
     * @param phone         手机
     * @param sexual        性别
     * @param imel
     * @param mail
     */
    void submitStudentInfo(@Param("studentId") String studentId,
                           @Param("password") String password,
                           @Param("phone") String phone,
                           @Param("sexual") String sexual,
                           @Param("imel") String imel,
                           @Param("mail") String mail);
}