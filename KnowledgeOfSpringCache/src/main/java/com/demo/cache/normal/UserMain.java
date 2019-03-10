package com.demo.cache.normal;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
public class UserMain {
    public static void main(String[] args) {
        UserService service = new UserService();

        service.getUserById("0001");
        service.getUserById("0001");


        service.reload();

        System.out.println("after reload");

        service.getUserById("0001");
        service.getUserById("0001");

    }
}
