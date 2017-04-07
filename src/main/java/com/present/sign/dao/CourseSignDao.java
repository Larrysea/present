
package com.present.sign.dao;


import com.present.sign.bean.CourseSign;

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
     * @param id 查询的数据信息条件
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
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);
}