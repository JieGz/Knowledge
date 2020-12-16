package com.demo.juc.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedDemo {

    /*
     * 1:修饰代码块,需要指定锁对象,进入同步代码块前要获得指定对象的锁.
     * 2:修饰实例方法,作用于当前实例加锁,进入方法前需要获得当前实例的锁.
     * 3:修饰静态方法,作用于当前类对象加锁,进入静态方法前需要获得类对象的锁.
     */
    public synchronized static void syn() {
        synchronized ("") {
            System.out.println("Synchronized");
        }
    }

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("我已经获得锁了");
        } finally {
            lock.unlock();
        }
    }
}
