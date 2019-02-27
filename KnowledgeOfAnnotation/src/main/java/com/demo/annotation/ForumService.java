package com.demo.annotation;

/**
 * @author 揭光智
 * @date 2019/02/27
 */
public class ForumService {

    @NeedTest(false)
    public void deleteForum(int forumId) {
        System.out.println("删除论坛模块" + forumId);
    }

    @NeedTest
    public void deleteTopic(int postId) {
        System.out.println("删除论坛主题" + postId);
    }
}
