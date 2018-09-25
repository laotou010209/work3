package com.laotou.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    //获取系统当前时间
    public static String refFormatNowDate() {
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sb=dateFormat.format(now);
        return String.valueOf(sb);
    }
}
