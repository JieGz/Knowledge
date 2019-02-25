package com.demo.aop.cglibproxy;

import com.demo.aop.jdkproxy.ForumServiceImpl;

/**
 * @author 揭光智
 * @date 2019/02/25
 */
public class CglibForumServiceTest {

    public static void main(String[] args) {

        CglibProxy proxy = new CglibProxy();
        //通过字节码技术动态生成代理类的实例
        ForumServiceImpl forumService = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        //通过代理类调用业务
        forumService.removeTopic(10);
        forumService.removeForum(1024);
    }
}
