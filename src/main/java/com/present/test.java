package com.present;

import com.present.common.util.MD5EncipherUtil;

import java.util.Date;


/**
 * Created by Larry-sea on 2017/3/16.
 */
public class test {

    final static String uuid = "609742e4a1ec4e2ead83bb427531bb04";

    public static void main(String[] args) {
        uuid.length();
        String md5String = MD5EncipherUtil.md516("609742e4a1ec4e2ead83bb427531bb04");
        String dateString = "2017-05-11 09:23:55";
        java.util.Date date1 = new java.util.Date();

        long time =1494475425730l;
        Date date = new Date(time);


        System.out.println(date);
    }
}
