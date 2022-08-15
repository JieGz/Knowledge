package com.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author jieguangzhi
 * @date 2022-07-04
 */
@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        log.info("项目开始工作...");
        final FileChangeListenerAdaptor adaptor = new FileChangeListenerAdaptor();
        String directoryName = "/Users/luke/work/learn/Knowledge/docs/logs";
        final FileChangeObserver observer = new FileChangeObserver(directoryName);
        observer.addListener(adaptor);
        final FileChangeMonitor monitor = new FileChangeMonitor(20L, observer);
        monitor.start();

        LockSupport.park();
        //System.out.println("项目结束工作...");
    }
}
