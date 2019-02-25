package com.demo.aop;

/**
 * 用于记录性能监视信息
 *
 * @author 揭光智
 * @date 2019/02/25
 */
public class MethodPerformance {

    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformance(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        //记录目标类方法开始执行的系统时间
        this.begin = System.currentTimeMillis();
    }

    public void printPerformance() {
        //获取目标类方法执行完成的系统时间
        this.end = System.currentTimeMillis();
        //计算出目标类方法执行的时间
        long elapse = end - begin;
        System.out.println(serviceMethod + "花费" + elapse + "毫秒");
    }
}
