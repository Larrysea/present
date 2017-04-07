
package com.present.sign.bean;

import com.present.common.annotation.UUID;

/**
 *
 * @ClassName: SignStartWithClass
 * @Description: 数据库表sign_start_with_class对应的entity
 */
public class SignStartWithClass
{
    /**
     * 选择班级签到的id
     */
    @UUID
    private String id;

    /**
     * 课程发起签到id
     */
    private String courseSignId;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 获取选择课程的id
     * @return id 选择课程的id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置选择课程的id
     * @param id 选择课程的id
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取课程发起签到id
     * @return courseSignId 课程发起签到id
     */
    public String getCourseSignId()
    {
         return courseSignId;
    }

    /**
     * 设置课程发起签到id
     * @param courseSignId 课程发起签到id
     */
    public void setCourseSignId(String courseSignId)
    {
         this.courseSignId = courseSignId;
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
}