package com.demo.aop.jdkproxy;

import com.demo.aop.ForumService;

import java.lang.reflect.Proxy;

/**
 * @author 揭光智
 * @date 2019/02/25
 */
public class JdkProxyForumServiceTest {

    public static void main(String[] args) {
        //希望被代理的业务类
        ForumServiceImpl forumService = new ForumServiceImpl();
        //将业务和横切逻辑编织在一起
        PerformanceHandler performanceHandler = new PerformanceHandler(forumService);
        //创建代理实例
        ForumService o = (ForumService) Proxy.newProxyInstance(
                forumService.getClass().getClassLoader(),
                forumService.getClass().getInterfaces(),
                performanceHandler);
        //通过代理实例调用业务方法
        o.removeTopic(10);
        o.removeForum(1024);
    }
}
