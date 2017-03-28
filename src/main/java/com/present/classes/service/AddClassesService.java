package com.present.classes.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.classes.dao.ClassesDao;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/18.
 * <p>
 * 添加班级信息
 */
@Service("addClasses")
public class AddClassesService extends BaseService<String> {

    @Autowired
    ClassesDao classesDao;


    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "schoolId", "className");

        return addClasses(params);
    }


    /**
     * 老师添加班级信息
     *
     * @param params 传入的请求jsonObject
     * @return 返回包含新添加的班级id信息
     */
    public ResponseDto<String> addClasses(final JSONObject params) {
        String schoolId = params.getString("schoolId");
        String classId = classesDao.queryIdByClassesNameAndSchoolId(params.getString("className"), schoolId);
        ResponseDto<String> responseDto;
        //如果不存在这个班级则添加该班级
        if (classId == null) {
            responseDto = new ResponseDto<String>();
            Classes classes = new Classes();
            classes.setSchoolId(schoolId);
            classes.setClassName(params.getString("className"));
            classesDao.insert(classes);
            responseDto.setData(classes.getId());
        }
        //如果已经存在该班级则抛出异常
        else {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("classes.addClasses.alreadyExist"));
        }
        return responseDto;
    }


}
