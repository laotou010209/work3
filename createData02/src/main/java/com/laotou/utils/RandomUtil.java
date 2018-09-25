package com.laotou.utils;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {
    /**
     * 生成指数从0到len之间的数
     *    注意不包含len
     * @param len
     * @return
     */
    public static int getSizeRandom(int len){
        return new Random().nextInt(len);
    }
}
