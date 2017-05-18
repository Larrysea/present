package com.present.course.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.DateUtil;
import com.present.course.bean.CourseClass;
import com.present.course.dao.CourseClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Larry-sea on 2017/5/12.
 * <p>
 * 添加班级到课程下面,建立联系
 */
@Service("addClassesToCourse")
public class AddTheClassesToCourse extends BaseService<String> {

    @Autowired
    CourseClassDao courseClassDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseId", "classIdArray");
        JSONArray jsonArray = params.getJSONArray("classIdArray");
        Date date = DateUtil.getDate();
        Classes classes;
        for (Object object : jsonArray) {
            classes = JSONObject.parseObject(object.toString(), Classes.class);
            CourseClass courseClass = new CourseClass();
            courseClass.setCourseId(params.getString("courseId"));
            courseClass.setClassId(classes.getId());
            courseClass.setDataState("1");
            courseClass.setStartTime(date);
            courseClassDao.insert(courseClass);
        }
        return new ResponseDto<String>();
    }
}
