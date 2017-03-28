package com.present.student.bean;

import com.present.common.annotation.UUID;

/**
 *
 * @ClassName: ClassStudent
 * @Description: 数据库表class_student对应的entity
 */
public class ClassStudent
{
    /**
     * 班级学生表的id
     */
    @UUID
    private String id;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 获取班级学生表的id
     * @return id 班级学生表的id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置班级学生表的id
     * @param id 班级学生表的id
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取班级id
     * @return classId 班级id
     */
    public String getClassId()
    {
         return classId;
    }

    /**
     * 设置班级id
     * @param classId 班级id
     */
    public void setClassId(String classId)
    {
         this.classId = classId;
    }

    /**
     * 获取学生id
     * @return studentId 学生id
     */
    public String getStudentId()
    {
         return studentId;
    }

    /**
     * 设置学生id
     * @param studentId 学生id
     */
    public void setStudentId(String studentId)
    {
         this.studentId = studentId;
    }
}