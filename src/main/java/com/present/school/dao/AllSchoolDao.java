/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.school.dao;


import com.present.school.bean.AllSchool;

import java.util.List;

public interface AllSchoolDao {
    /**
     * 将数据插入数据库表中
     *
     * @param allSchool 插入的数据信息
     */
    void insert(AllSchool allSchool);

    /**
     * 根据key查找数据库中信息
     *
     * @param school_id 查询的数据信息条件
     * @return AllSchool 符合条件的数据信息
     */
    AllSchool queryByKey(String school_id);

    /**
     * 根据key更新数据库中信息
     *
     * @param allSchool 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(AllSchool allSchool);

    /**
     * 根据key删除数据库中信息
     *
     * @param school_id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String school_id);


    /**
     * 获取所有学校数据接口
     *
     * @return   返回学校list
     */
    List<AllSchool> getAllSchool();


}