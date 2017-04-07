
package com.present.school.bean;

/**
 *
 * @ClassName: AllSchool
 * @Description: 数据库表all_school对应的entity
 */
public class AllSchool
{
    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 获取学校id
     * @return schoolId 学校id
     */
    public String getSchoolId()
    {
         return schoolId;
    }

    /**
     * 设置学校id
     * @param schoolId 学校id
     */
    public void setSchoolId(String schoolId)
    {
         this.schoolId = schoolId;
    }

    /**
     * 获取学校名称
     * @return schoolName 学校名称
     */
    public String getSchoolName()
    {
         return schoolName;
    }

    /**
     * 设置学校名称
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName)
    {
         this.schoolName = schoolName;
    }
}