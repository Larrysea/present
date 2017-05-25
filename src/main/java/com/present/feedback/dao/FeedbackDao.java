
package com.present.feedback.dao;


import com.present.feedback.bean.Feedback;


/*
* 反馈的dao层
*
* */
public interface FeedbackDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param feedback 插入的数据信息
     */
    void insert(Feedback feedback);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id
     * @return Feedback 符合条件的数据信息
     */
    Feedback queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param feedback 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(Feedback feedback);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id
     * @return 删除数据的行数
     */
    int deleteByKey(String id);
}