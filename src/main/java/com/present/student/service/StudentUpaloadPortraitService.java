package com.present.student.service;

import com.present.common.service.BaseService;
import com.present.common.service.ReadExcel;
import com.present.student.dao.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Larry-sea on 2017/3/23.
 * <p>
 * 学生上传头像接口
 */

@Service("studentUploadPortrait")
public class StudentUpaloadPortraitService extends BaseService<String> {
    private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    @Autowired
    StudentDao studentDao;


}


