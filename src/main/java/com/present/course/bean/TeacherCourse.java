
package com.present.course.bean;

import com.present.common.annotation.UUID;

/**
 * @ClassName: TeacherCourse
 * @Description: 数据库表teacher_course对应的entity
 */
public class TeacherCourse {
    /**
     * 老师课程数据id
     */
    @UUID
    private String id;

    /**
     * 老师id
     */
    private String teacherId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 数据状态
     */
    private String dataState;

    /**
     * 获取老师课程数据id
     *
     * @return id 老师课程数据id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置老师课程数据id
     *
     * @param id 老师课程数据id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取老师id
     *
     * @return teacherId 老师id
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 设置老师id
     *
     * @param teacherId 老师id
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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