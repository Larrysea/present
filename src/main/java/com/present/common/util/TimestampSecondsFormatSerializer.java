package com.present.common.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;

public final class TimestampSecondsFormatSerializer implements ObjectSerializer {

	/**
	 * 日期型字段转换为秒后输出。
	 * 
	 * @param serializer serializer
	 * @param object 对象值
	 * @param obj1 obj1对象
	 * @param type type对象
	 */
	@Override
	public void write(JSONSerializer serializer, Object object, Object obj1,
			Type type, int i) throws IOException {
		if (object == null)
		{
			serializer.getWriter().writeNull();
			return;
		}
		else 
		{
			Timestamp date = (Timestamp) object;
			long seconds = date.getTime() / 1000;
			serializer.write(String.valueOf(seconds));
			return;
		}
	}
}