package com.present.student.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/25.
 * <p>
 * 获取头像服务，返回头像的路径
 */
@Service("getStudentPortraitPath")
public class GetStudentPortraitService extends BaseService<String> {


    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "studentId");
        ResponseDto<String> responseDto = new ResponseDto<String>();
        String portraitPath = "resource/" + params.getString("studentId") + "_head.png";
        responseDto.setData(portraitPath);
        return responseDto;
    }
}
