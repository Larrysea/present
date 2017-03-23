/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.sign.dao;


import com.present.sign.bean.StudentSign;

import java.util.List;

public interface StudentSignDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param studentSign 插入的数据信息
     */
    void insert(StudentSign studentSign);

    /**
     * 根据key查找数据库中信息
     * 
     * @param id 查询的数据信息条件
     * @return StudentSign 符合条件的数据信息
     */
    StudentSign queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param studentSign 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(StudentSign studentSign);

    /**
     * 根据key删除数据库中信息
     * 
     * @param id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);

    /**
     * 根据课程签到id
     *
     * @param courseSignId
     * @return  返回学生签到情况列表
     */
    List<StudentSign> getStudentSignInfoList(String courseSignId);



    /**
     * 修改学生的签到状态
     * @param courseSignId   课程签到id
     * @param studentId      学生id
     * @param signState      签到状态
     */
    void changeStudentSignState(String courseSignId,String studentId,String signState);

}

