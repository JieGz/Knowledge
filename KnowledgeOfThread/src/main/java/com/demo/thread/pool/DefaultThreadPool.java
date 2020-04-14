package com.demo.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 揭光智
 * @date 2020/04/14
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /** 线程池最大的工作线程数 */
    private static final int MAX_WORKER_NUMBERS = 10;

    /** 线程池默认的工作线程数 */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /** 线程池最小的工作线程数 */
    private static final int MIN_WORKER_NUMBERS = 1;

    /** 这是一个任务列表,将会向这个列表中插入任务 */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /** 工作线程列表 */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /** 工作线程的数量 */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /** 线程编号生成 */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
        initializeWorkers(workerNum);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num > this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }

            int count = 0;

            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    ++count;
                }
            }

            this.workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    /**
     * 初始化工作线程
     *
     * @param num 线程
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    /** 工作线程,负责消息任务 */
    class Worker implements Runnable {
        /** 是否工作,volatile保证了可见性 */
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job;
                synchronized (jobs) {
                    //这里是典型的等待,通知机制
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知外面对WorkerThread的中断操作,就返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        //忽略Job中执行的异常
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
