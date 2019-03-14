package com.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2019/03/14
 */
public class ReadThread extends Thread {
    private static int num = 0;
    private static boolean ready = false;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (ready) {
                System.out.println(num + num);
            }
            System.out.println("read thread...");
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread set over ...");
        }
    }

    public static void main(String[] args) throws InterruptedException {


        ReadThread readThread = new ReadThread();
        WriteThread writeThread = new WriteThread();
        readThread.start();
        writeThread.start();

        TimeUnit.MILLISECONDS.sleep(1);

        readThread.interrupt();
        System.out.println(" main thread exit");
    }

}
