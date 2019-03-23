package com.demo.juc.tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport工具类,它的主要作用是挂起和唤醒线程,该工具类是创建锁和其他同步类的基础
 * <p>
 * LockSupport类与每个使用它的线程都会关联一个许可证,在默认情况调用LockSupport类的方法的线程是不持有许可证的.
 * LockSupport的底层是使用Unsafe类.
 * <p>
 * 如果调用{@link LockSupport#park}方法的线程已经持有了和LockSupport关联的许可证,则调用{@link LockSupport#park}时会马上返回,
 * 否则调用线程会被禁止参与线程调度,也就会被阻塞挂起.在其他线程调用unPark(Thread thread)方法并且把将当前线程作为参数时,调用park()
 * 而被阻塞的线程会返回;另外,如果其他线程调用了阻塞线程的interrupt()方法,设置了中断标示或者线程被虚假唤醒,则阻塞线程也会返回,所以在调
 * 用park方法时最好也使用循环条件判断方式.但需要注意一个问题,因调用park()方法而被阻塞的线程被其他线程中断而返回时并不会抛出
 * InterruptedException.
 * <p>
 * park方法返回时不会告诉你因何种原因返回,所以调用者需要根据之前调用park方法的原因,再次检查条件是否满足,如果不满足还需要再次调用park方法
 * <p>
 * 扩展知识:
 * Unsafe: void park(boolean isAbsolute, long time) 阻塞当前线程,其中的参数isAbsolute等于false且time等于0表示一直阻塞.time>0
 * 表示等待指的time后阻塞线程会被唤醒,这个time是一个相对值,是个增量值,也就是相对当前时间累加time后当前线程就会被唤醒.
 * 如果isAbsolute等于true,并且time大于0,则表示阻塞的线程到指定的时间点就会被唤醒,这里的time是一个绝对时间,是将某个时间点换算为ms后的值.
 * 另外,当其他线程调用了当前阻塞线程的interrupt方法而中断了当前线程时,当前线程也会返回,而当其他线程调用了unPark方法并且把当前线程作为参数
 * 时当前线程也会返回.
 * 在理解了Unsafe类的park方法后,是不是LockSupport的方法一下就理解了呢.底层嘛.
 *
 * @author 揭光智
 * @date 2019/03/23
 */
public class TestLockSupport {

    public void testPark() {
        LockSupport.park(this);
    }

    public static void main(String[] args) throws Exception {
        new TestLockSupport().testPark();
    }


    private static void test1() {
        System.out.println(" begin park!");

        LockSupport.park();

        System.out.println(" end park!");
    }


    private static void test2() {
        System.out.println(" begin park!");
        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println(" end park!");
    }


    private static void test3() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("Child thread begin park!");
            LockSupport.park();
            System.out.println("Child thread unPark!");
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("main thread begin unPark");

        LockSupport.unpark(thread);
    }

    private static void test4() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("Child thread begin park!");
            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
            System.out.println("Child thread unPark!");
        });


        thread.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("main thread begin unPark");

        //LockSupport.unpark(thread);
        thread.interrupt();
    }


}
