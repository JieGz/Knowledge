package com.demo.aop;

/**
 * @author 揭光智
 * @date 2019/02/25
 */
public class TestForumService {

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeTopic(10);
        forumService.removeForum(1024);
    }
}
