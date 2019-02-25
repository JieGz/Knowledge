package com.demo.aop;

/**
 * 性能监视实现类
 *
 * @author 揭光智
 * @date 2019/02/25
 */
public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<>();

    public static void begin(String method) {
        System.out.println("begin monitor...");
        MethodPerformance methodPerformance = new MethodPerformance(method);
        performanceRecord.set(methodPerformance);
    }

    public static void end() {
        System.out.println("end monitor.");
        MethodPerformance methodPerformance = performanceRecord.get();
        //打印方法性能监视的结果信息
        methodPerformance.printPerformance();
    }
}
