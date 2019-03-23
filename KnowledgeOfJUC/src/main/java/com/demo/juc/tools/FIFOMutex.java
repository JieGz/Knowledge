package com.demo.juc.tools;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 揭光智
 * @date 2019/03/23
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean();
    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread thread = Thread.currentThread();
        waiters.add(thread);

        //只有队首的线程可以获取锁
        while (waiters.peek() != thread && !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }

        waiters.remove();
        if (wasInterrupted) {
            thread.interrupt();
        }
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
