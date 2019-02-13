package com.demo.design.pattern.strategy;

/**
 * @author 揭光智
 * @date 2019/02/13
 */
public class ZhaoYun {
    public static void main(String[] args) {
        Context context;

        System.out.println("---刚刚到吴国的时候拆第一个---");
        //拿到妙计
        context = new Context(new BackDoor());
        //拆开执行
        context.operate();
        System.out.println();

        //刘备乐不思蜀了,拆第二个了
        System.out.println("---刘备乐不思蜀了,拆第二个了---");
        context = new Context(new GivenGreenLight());
        //执行了第二个锦囊
        context.operate();
        System.out.println();

        //孙权的小兵追来了,咋办?拆第三个
        System.out.println("---孙权的小兵追来了,咋办?拆第三个---");
        context = new Context(new BlockEnemy());
        //孙夫人退兵
        context.operate();
        System.out.println();
    }
}
