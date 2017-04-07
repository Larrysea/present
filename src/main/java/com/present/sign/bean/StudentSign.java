
package com.present.sign.bean;

import com.present.common.annotation.UUID;

import java.util.Date;

/**
 *
 * @ClassName: StudentSign
 * @Description: 数据库表student_sign对应的entity
 */
public class StudentSign
{
    /**
     * 学生签到的id
     */
    @UUID
    private String id;

    /**
     * 课程发起签到id
     */
    private String courseSignId;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 签到状态
     */
    private String signState;

    /**
     * 获取学生签到的id
     * @return id 学生签到的id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置学生签到的id
     * @param id 学生签到的id
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

    /**
     * 获取签到时间
     * @return signTime 签到时间
     */
    public Date getSignTime()
    {
         return signTime;
    }

    /**
     * 设置签到时间
     * @param signTime 签到时间
     */
    public void setSignTime(Date signTime)
    {
         this.signTime = signTime;
    }

    /**
     * 获取签到状态
     * @return signState 签到状态
     */
    public String getSignState()
    {
         return signState;
    }

    /**
     * 设置签到状态
     * @param signState 签到状态
     */
    public void setSignState(String signState)
    {
         this.signState = signState;
    }
}