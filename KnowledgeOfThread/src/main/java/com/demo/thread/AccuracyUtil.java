package com.demo.thread;

import java.util.Random;

/**
 * ================================================
 * 作者:Je(揭光智)     联系方式:QQ:364049613
 * 版本:
 * 创建日期:2019/9/16 0016
 * 描述:
 * 修订历史：
 * ================================================
 */
public class AccuracyUtil {

    /**
     * 随机生成精度的方法,返回5-15之间的精度
     *
     * @return
     */
    public static float getAccuracy() {
        Random random = new Random();
        return random.nextInt(11) + 10005;
    }
}
