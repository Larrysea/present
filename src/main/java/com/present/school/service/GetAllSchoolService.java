package com.present.school.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.school.bean.AllSchool;
import com.present.school.dao.AllSchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/4/6.
 * <p>
 * 获取所有校名的接口
 */
@Service("getAllSchool")
public class GetAllSchoolService extends BaseService<List<AllSchool>> {

    @Autowired
    AllSchoolDao allSchoolDao;

    @Override
    public ResponseDto<List<AllSchool>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        List<AllSchool> schoolList = allSchoolDao.getAllSchool();
        ResponseDto<List<AllSchool>> allSchoolRsp = new ResponseDto<List<AllSchool>>();
        allSchoolRsp.setData(schoolList);
        return allSchoolRsp;
    }
}
