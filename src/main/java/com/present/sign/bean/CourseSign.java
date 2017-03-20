/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.sign.bean;

import com.present.common.annotation.Now;
import com.present.common.annotation.UUID;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 *
 * @ClassName: CourseSign
 * @Description: 数据库表course_sign对应的entity
 */
public class CourseSign
{
    /**
     * 课程发起签到id
     */
    @UUID
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 发起签到的时间
     */
    @Now
    private Date createTime;

    /**
     * 签到发起类型
     */
    private String signStartType;

    /**
     * 获取课程发起签到id
     * @return id 课程发起签到id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置课程发起签到id
     * @param id 课程发起签到id
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取课程id
     * @return courseId 课程id
     */
    public String getCourseId()
    {
         return courseId;
    }

    /**
     * 设置课程id
     * @param courseId 课程id
     */
    public void setCourseId(String courseId)
    {
         this.courseId = courseId;
    }

    /**
     * 获取发起签到的时间
     * @return createTime 发起签到的时间
     */
    public Date getCreateTime()
    {
         return createTime;
    }

    /**
     * 设置发起签到的时间
     * @param createTime 发起签到的时间
     */
    public void setCreateTime(Date createTime)
    {
         this.createTime = createTime;
    }

    /**
     * 获取签到发起类型
     * @return signStartType 签到发起类型
     */
    public String getSignStartType()
    {
         return signStartType;
    }

    /**
     * 设置签到发起类型
     * @param signStartType 签到发起类型
     */
    public void setSignStartType(String signStartType)
    {
         this.signStartType = signStartType;
    }
}