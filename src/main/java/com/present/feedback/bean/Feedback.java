
package com.present.feedback.bean;

import com.present.common.annotation.Now;
import com.present.common.annotation.UUID;

import java.util.Date;

/**
 *
 * @ClassName: Feedback
 * @Description: 数据库表feedback对应的entity
 */
public class Feedback
{
    /**
     * id
     */
    @UUID
    private String id;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 反馈内容
     */
    private String feedBackContent;

    /**
     * 提交时间
     */
    @Now
    private Date submitDate;

    /**
     * 操作系统信息
     */
    private String osInfo;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 获取id
     * @return id id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置id
     * @param id id
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取用户id
     * @return userid 用户id
     */
    public String getUserid()
    {
         return userid;
    }

    /**
     * 设置用户id
     * @param userid 用户id
     */
    public void setUserid(String userid)
    {
         this.userid = userid;
    }

    /**
     * 获取联系方式
     * @return contactWay 联系方式
     */
    public String getContactWay()
    {
         return contactWay;
    }

    /**
     * 设置联系方式
     * @param contactWay 联系方式
     */
    public void setContactWay(String contactWay)
    {
         this.contactWay = contactWay;
    }

    /**
     * 获取反馈内容
     * @return feedBackContent 反馈内容
     */
    public String getFeedBackContent()
    {
         return feedBackContent;
    }

    /**
     * 设置反馈内容
     * @param feedBackContent 反馈内容
     */
    public void setFeedBackContent(String feedBackContent)
    {
         this.feedBackContent = feedBackContent;
    }

    /**
     * 获取提交时间
     * @return submitDate 提交时间
     */
    public Date getSubmitDate()
    {
         return submitDate;
    }

    /**
     * 设置提交时间
     * @param submitDate 提交时间
     */
    public void setSubmitDate(Date submitDate)
    {
         this.submitDate = submitDate;
    }

    /**
     * 获取操作系统信息
     * @return osInfo 操作系统信息
     */
    public String getOsInfo()
    {
         return osInfo;
    }

    /**
     * 设置操作系统信息
     * @param osInfo 操作系统信息
     */
    public void setOsInfo(String osInfo)
    {
         this.osInfo = osInfo;
    }

    /**
     * 获取设备信息
     * @return deviceInfo 设备信息
     */
    public String getDeviceInfo()
    {
         return deviceInfo;
    }

    /**
     * 设置设备信息
     * @param deviceInfo 设备信息
     */
    public void setDeviceInfo(String deviceInfo)
    {
         this.deviceInfo = deviceInfo;
    }
}