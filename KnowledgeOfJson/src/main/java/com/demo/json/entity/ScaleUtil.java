package com.lucky.ws.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 一起来捉妖服务器数据交互加密和解密
 *
 * @author 揭光智
 * @date 2019/10/09
 */
public class ScaleUtil {

    public static double scale(double d, int i) {
        try {
            if (Double.isNaN(d)) {
                return 0.0d;
            }
            return BigDecimal.valueOf(d).setScale(i, RoundingMode.HALF_DOWN).doubleValue();
        } catch (Exception e) {
            return 0.0d;
        }
    }
}
