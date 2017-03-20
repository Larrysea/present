package com.present.student.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.student.baen.Student;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/18.
 * <p>
 * 添加学生信息
 */
public class AddStudentService extends BaseService {

    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        return super.process(params, request, response);
    }


    /**
     * 批量添加学生信息
     *
     * @param params
     * @return
     */
    public ResponseDto addStudent(JSONObject params) {
        Student student = new Student();
        JSONObject jsonObject;
        JSONArray jsonArray = params.getJSONArray("studentList");
        for (int position = 0; position < jsonArray.size(); position++) {
            jsonObject = (JSONObject) jsonArray.get(position);
            student.setName(jsonObject.getString("name"));
            student.setStudentNumber(jsonObject.getString("studentNumber"));
            studentDao.insert(student);
        }
        return new ResponseDto();
    }
}
