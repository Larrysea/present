package com.present.classes.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.classes.dao.ClassesDao;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/18.
 *
 * 添加班级信息
 */
public class AddClassesService extends BaseService<String> {

    @Autowired
    ClassesDao classesDao;


    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("school_id"));

        return addClasses(params,params.getString("schoolId"));
    }


    /**
     * 老师添加班级信息
     *
     * @param params     传入的请求jsonObject
     * @param schoolId   学校id
     * @return
     */
    public ResponseDto<String> addClasses(final JSONObject params, final String schoolId) {
        Classes classes = new Classes();
        classesDao.queryIdByClassesNameAndSchoolId(params.getString("schoolName"), schoolId);
        classes.setClassName(params.getString("schoolName"));
        classes.setSchoolId(schoolId);
        classesDao.insert(classes);
        String id = classesDao.queryIdByClassesNameAndSchoolId(params.getString("schoolName"), schoolId);
        ResponseDto<String> response = new ResponseDto<String>();
        response.setData(id);
        return response;
    }





}
