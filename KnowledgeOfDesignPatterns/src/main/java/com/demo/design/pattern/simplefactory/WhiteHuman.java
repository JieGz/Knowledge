package com.demo.design.pattern.simplefactory;

/**
 * @author 揭光智
 * @date 2019/02/13
 */
public class WhiteHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白色的!");
    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话,一般都是但是单字节.");
    }
}
