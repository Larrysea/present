package com.present.classes.service;

import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.classes.dao.ClassesDao;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/12.
 * <p>
 * 获取classInfo
 */
@Service("getClassInfo")
public class GetClassInfo extends BaseService<Classes> {
    /**
     * 班级查询dao
     */
    @Autowired
    ClassesDao classesDao;

    @Override
    public ResponseDto<Classes> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "className", "schoolId");
        Classes classInfo = classesDao.getClassInfo(params.getString("className"), params.getString("schoolId"));
        ResponseDto<Classes> responseDto;
        responseDto = new ResponseDto<Classes>();
        responseDto.setData(classInfo);
        return responseDto;
    }
}
