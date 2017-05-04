package com.present.common.config;

/**
 * Created by Larry-sea on 2017/3/23.
 * <p>
 * 常量配置类
 */
public class Constants {
    /*
    * 学生缺勤
    * */
    public final static String ABSENCE = "3";


   /*
   *
   * 学生签到
   *
   * */

    public final static String SIGN = "1";


    /*
    *
    * 学生病假
    *
    * */
    public final static String SICK_LEAVE = "2";


    /*
    *
    * 学生登录
    *
    * */
    public final static int STUDENT_SIGN = 0x1;


    /*
    * 修改学生签到状态类型
    *
    * */
    public final static int CHANGE_STUDENT_SIGN = 0x2;


    /*
    * 在使用时是否参加该课程接口时作为返回参数使用
    *
    * 已参加该课程
    *
    * */
    public final static String ALREADY_JOIN_IN = "ALREADY_JOIN_IN";


    /**
     * 在使用时是否参加该课程接口时作为返回参数使用
     *
     * 没有参加该课程
     */
    public final static String NOT_JOIN_THE_COURSE = "NOT_JOIN_THE_COURSE";

}
