package com.demo.thread;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @author 揭光智
 * @date 2019/09/16
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(AccuracyUtil.getAccuracy());

        double a2 = 23.5485467815;
        double a3 = 113.546748166;

        System.out.println("a2 = " + a(a2));
        System.out.println("a3 = " + a(a3));
        System.out.println();
        System.out.println("a2 = " + ((1000000.0d * a2) % 1000.0d == 0.0d));
        System.out.println("a3 = " + ((1000000.0d * a3) % 1000.0d == 0.0d));
        System.out.println();
        System.out.println("a2 = " + (Math.abs(a2) < 1.0E-8d));
        System.out.println("a3 = " + (Math.abs(a3) < 1.0E-8d));
        System.out.println();
        System.out.println("a2 = " + (Math.abs(a2 - 1.0d) < 1.0E-8d));
        System.out.println("a3 = " + (Math.abs(a3 - 1.0d) < 1.0E-8d));
        System.out.println();
        System.out.println("a2 = " + (a2 < -90.0d || a2 > 90.0d));
        System.out.println("a3 = " + (a3 < -180.0d || a3 > 180.0d));

        System.out.println(m495a(a2, 1));

        System.out.println(m495a(getGpsAccuracy(), 1));
    }


    private static boolean a(double d2) {
        if (Math.abs(((double) Double.valueOf(d2).longValue()) - d2) < Double.MIN_VALUE) {
            return true;
        }
        return false;
    }


    /**
     * 随机生成一个10005.0-10006.0之间的double
     *
     * @return gps精度
     */
    public static double getGpsAccuracy() {
        Random random = new Random();
        return random.nextDouble() + random.nextInt(20) + 10005;
    }

    public static double m495a(double d, int i) {
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
