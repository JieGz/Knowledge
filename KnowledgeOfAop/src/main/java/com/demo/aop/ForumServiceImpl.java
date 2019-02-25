package com.demo.aop;

import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2019/02/25
 */
public class ForumServiceImpl implements ForumService {

    @Override
    public void removeTopic(int topicId) {
        PerformanceMonitor.begin("com.demo.aop.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录：" + topicId);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PerformanceMonitor.end();
    }

    @Override
    public void removeForum(int forumId) {
        PerformanceMonitor.begin("com.demo.aop.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录：" + forumId);
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PerformanceMonitor.end();
    }
}
