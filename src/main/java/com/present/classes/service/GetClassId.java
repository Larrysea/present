package com.present.classes.service;

import com.alibaba.fastjson.JSONObject;
import com.present.classes.dao.ClassesDao;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/5.
 * <p>
 * 获取班级的id服务
 */
@Service("getClassId")
public class GetClassId extends BaseService<String> {


    /**
     * 班级查询dao
     */
    @Autowired
    ClassesDao classesDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "className", "schoolId");
        String classId = classesDao.queryIdByClassesNameAndSchoolId(params.getString("className"), params.getString("schoolId"));
        ResponseDto<String> responseDto;
        responseDto = new ResponseDto<String>();
        responseDto.setData(classId);
        return responseDto;
    }
}
