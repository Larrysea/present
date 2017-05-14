/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.sign.dao;


import com.present.sign.bean.CourseSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSignDao {
    /**
     * 将数据插入数据库表中
     *
     * @param courseSign 插入的数据信息
     */
    void insert(CourseSign courseSign);

    /**
     * 根据key查找数据库中信息
     *
     * @param id
     * @return CourseSign 符合条件的数据信息
     */
    CourseSign queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     *
     * @param courseSign 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(CourseSign courseSign);

    /**
     * 根据key删除数据库中信息
     *
     * @param id
     * @return 删除数据的行数
     */
    int deleteByKey(String id);


    /**
     * 根据课程id获取最后一次的课程签到id
     *
     * @param courseId 课程id
     * @return
     */
    String queryTheLatestCourseSignId(@Param("courseId") String courseId);


    /**
     * 获取课程的所有签到信息
     *
     * @param courseId
     * @return
     */
    List<CourseSign> getCourseAllSignInfo(@Param("courseId") String courseId);
}