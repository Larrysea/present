package com.present.common.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串常用工具类
 * Created by Larry on 4/20/2016.
 */
public class MyStringUtil {

    final static String TAG = "String util";







    /*
    *
    * 检查是否是数字还是字母
    * 如果是返回结果为true
    *
    * */

    static public boolean checkNumberOrChar(char parm) {

        if (parm >= 48 && parm <= 59 || parm >= 65 && parm <= 90) {
            return true;
        }

        return false;
    }





    /*
    * 输入流转换为字符串
    *
    * */
    static public String inputStreamToString(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length;
        String result = null;
        try {
            while ((length = inputStream.read()) != -1) {
                byteArrayOutputStream.write(length);
                length += length;
                if (length > 700) {
                    return null;
                }
            }
            byteArrayOutputStream.close();
            result = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        } catch (IOException e) {

        }
        return result;

    }


    /*
    *
    * 返回源字符串中的数字字符串
    *
    * 例如 fasdfasf132123fasd554
    *
    * 返回的list则是 132123  和 554
    *
    * */
    static public List<String> getNumberFromString(String sourceString, int minLength, int maxLength) {


        List<String> result = new ArrayList<String>();
        char tempChar;
        String addString = "";
        for (int position = 0; position < sourceString.length(); position++) {
            boolean isNumber;     //是数字
            tempChar = sourceString.charAt(position);
            if (Character.isDigit(tempChar)) {
                addString += tempChar;
                isNumber = true;
            } else if ((Character.isLetter(tempChar) && addString.length() < minLength) || addString.length() > maxLength) {
                addString = "";
                isNumber = false;
            } else if (Character.isLetter(tempChar) && addString.length() <= maxLength) {
                if (isNumber = true) {
                    result.add(addString);
                    addString = "";
                }
            }

        }

        return result;
    }


    /**
     * 把InputStream中的内容读出来，放到一个byte[]中返回
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] getBytes(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        baos.close();
        return baos.toByteArray();
    }


}
