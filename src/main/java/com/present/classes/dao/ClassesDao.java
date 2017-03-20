/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.classes.dao;


import com.present.classes.bean.Classes;

public interface ClassesDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param classes 插入的数据信息
     */
    void insert(Classes classes);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id 查询的数据信息条件
     * @return Classes 符合条件的数据信息
     */
    Classes queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param classes 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(Classes classes);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);

    /**
     *
     * 更具班级名称和学校id查询是否存在这个班级
     * @param classesName
     * @param schoolId
     * @return
     */
    String queryIdByClassesNameAndSchoolId(String classesName,String schoolId);


}