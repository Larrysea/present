/**
 * 南京青牛通讯技术有限公司
 * 日期：$$Date: 2016-11-04 14:42:55 +0800 (星期五, 04 十一月 2016) $$
 * 作者：$$Author: zhoulin $$
 * 版本：$$Rev: 134473 $$
 * 版权：All rights reserved.
 */
package com.present.common.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseService<T> implements IBaseService<T> {

    /**
     * process 处理POST请求返回结果，包括返回码和返回码描述
     *
     * @param params   业务参数
     * @param request  request对象
     * @param response response对象
     * @return 返回码及描述
     */
    public ResponseDto<T> process(JSONObject params,
                                  HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
