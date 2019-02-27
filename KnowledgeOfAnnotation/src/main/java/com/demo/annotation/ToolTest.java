package com.demo.annotation;

import java.lang.reflect.Method;

/**
 * @author 揭光智
 * @date 2019/02/27
 */
public class ToolTest {

    public static void main(String[] args) {
        Class<ForumService> serviceClass = ForumService.class;
        Method[] methods = serviceClass.getDeclaredMethods();

        for (Method method : methods) {
            NeedTest needTest = method.getAnnotation(NeedTest.class);
            if (needTest != null) {
                if (needTest.value()) {
                    System.out.println(method.getName() + "()需要测试");
                } else {
                    System.out.println(method.getName() + "()不需要测试");
                }
            }
        }
    }
}
