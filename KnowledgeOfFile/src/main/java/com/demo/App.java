package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.comparator.NameFileComparator;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
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
        //final FileFilter filter = new DateFilter("20220815083200");
        final FileFilter filter = new DateFilter();
        final FileChangeObserver observer = new FileChangeObserver(directoryName, filter);
        //final FileChangeObserver observer = new FileChangeObserver(directoryName);
        observer.addListener(adaptor);
        final FileChangeMonitor monitor = new FileChangeMonitor(5000L, observer);
        monitor.start();

        LockSupport.park();
        //System.out.println("项目结束工作...");
    }
}
