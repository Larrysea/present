
package com.present.course.bean;

import com.present.common.annotation.Now;
import com.present.common.annotation.UUID;

import java.util.Date;

/**
 * @ClassName: CourseClass
 * @Description: 数据库表course_class对应的entity
 */

public class CourseClass {
    /**
     * courseClass  表 id
     */
    @UUID
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 启动时间
     */
    @Now
    private Date startTime;


    /**
     * 数据状态
     */
    private String dataState;

    /**
     * 获取courseClass  表 id
     *
     * @return id courseClass  表 id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置courseClass  表 id
     *
     * @param id courseClass  表 id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取课程id
     *
     * @return courseId 课程id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 设置课程id
     *
     * @param courseId 课程id
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取班级id
     *
     * @return classId 班级id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 获取启动时间
     *
     * @return startTime 启动时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置启动时间
     *
     * @param startTime 启动时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    /**
     * 获取数据状态
     *
     * @return dataState 数据状态
     */
    public String getDataState() {
        return dataState;
    }

    /**
     * 设置数据状态
     *
     * @param dataState 数据状态
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }
}