package com.demo.thread.pool;

/**
 * @author 揭光智
 * @date 2020/04/14
 */
public interface ThreadPool<Job extends Runnable> {

    //执行一个Job,这个Job需要实现Runnable接口
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作线程
    void addWorkers(int num);

    //减少工作线程
    void removeWorker(int num);

    //获取正在等待执行的任务数量
    int getJobSize();
}
