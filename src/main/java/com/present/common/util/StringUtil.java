package com.present.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils
{
    /**
     * 传入字符串，指定位数不足的场合，自动左位补零
     * 
     * @param str 字符串
     * @param len 字符串
     * @return 格式化后的字符串
     */
    public static String padLeft(String str, int len)
    {
        int slen = str.getBytes().length;
        StringBuffer sb = new StringBuffer();
        if (slen < len)
        {
            for (int i = 0; i < (len - slen); i++)
            {
                sb.append("0");
            }
            sb.append(str);
        }
        else
        {
            sb.append(str);
        }
        
        return sb.toString();
    }
    
    /**
     * 异常PrintStraceString输出字符串
     * 
     * @param Throwable 异常
     * @return字符串
     */
    public static String getPrintStraceString(Throwable e)
    {
    	StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        
        return sw.toString();
    } 
    
    public static String getUUIDString() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) 
				+ s.substring(19, 23) + s.substring(24);
	}
    
    public static boolean isRspSucess(int code) {
    	// 有异常时直接返回
    	if ((code % 1000 != 0) && code != 1) {
    		return false;
    	} else {
    		return true;
    	}
    }
}
