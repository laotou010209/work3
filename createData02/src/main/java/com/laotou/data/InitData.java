package com.laotou.data;

import com.laotou.utils.RandomUtil;

public class InitData {
    //浏览器
    private static final String[] Browsers = {"ie","chorm","firefox","chrome","360"};
    //分隔符字段
    private static final String DELIMITER = "\t";
    //行分隔符
    private static final String LINE_DELIMITER = "\n";
    /**
     * 得到一行数据
     *
     * @return
     */
    public static String getLineData() {
        String str=CurrentTime.refFormatNowDate()+DELIMITER+RandomIp.getRandomIp()+DELIMITER+Browsers[RandomUtil.getSizeRandom(Browsers.length)]+LINE_DELIMITER;
        return str.toString();
    }
}
