package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.DateUtil;
import com.present.sign.dao.StudentSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/23.
 * <p>
 * 学生签到接口和老师修改学生签到状态接口
 * <p>
 * 当是学生
 */
@Service("changeStudentSignState")
public class ChangeStudentSignService extends BaseService {


    @Autowired
    StudentSignDao studentSignDao;


    /**
     * @param params   业务参数 课程id，学生id，签到时间，签到类型  这个有两种类型一种是签到类型一种是修改签到状态类型
     * @param request  request对象
     * @param response response对象
     * @return
     */
    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignId", "studentId", "signTime", "changeType");
        if (null != params.getString("signType")) {

            //todo 学生签到的时候需要检查客户端传过来的id是否是他的课程的id而不是其他教室的wifi信号传过来的id
            //学生签到
            if (params.getString("signType").equals(Constants.STUDENT_SIGN)) {
                studentSignDao.changeStudentSignState(params.getString("courseSignId"),
                        params.getString("studentId"),
                        DateUtil.convertSecondsStringToDate(params.getString("signTime")),
                        Constants.SIGN);
            }
            //修改学生签到状态
            else if (params.getString("signType").equals(Constants.CHANGE_STUDENT_SIGN)) {
                studentSignDao.changeStudentSignState(params.getString("courseSignId"),
                        params.getString("studentId"),
                        DateUtil.getDate(), params.getString("signType"));

            }

        }
        return new ResponseDto();
    }
}
