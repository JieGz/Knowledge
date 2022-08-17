package com.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author jieguangzhi
 * @date 2022-07-04
 */
@Slf4j
public class FileChangeListenerAdaptor implements FileChangeListener {
    @Override
    public void onStart(FileChangeObserver observer) {
        // log.info("FileChangeListenerAdaptor#onStart");
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info(directory.getName() + "->目录被创建");
    }

    @Override
    public void onDirectoryChange(File directory) {
        //log.info(directory.getPath() + "->目录被修改");
    }

    @Override
    public void removeDirectoryMonitor(File directory) {
        log.info(directory.getPath() + "->目录移除监听");
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info(directory.getPath() + "->目录被删除");
    }

    @Override
    public void onFileCreate(File file) {
        log.info(file.getPath() + "->文件被创建");
    }

    @Override
    public void onFileChange(File file) {
        log.info(file.getPath() + "->文件被修改");
    }


    @Override
    public void removeFileMonitor(File file) {
        log.info(file.getPath() + "->移除监听");
    }

    @Override
    public void onFileDelete(File file) {
        log.info(file.getPath() + "->文件被删除");
    }

    @Override
    public void onStop(FileChangeObserver observer) {
        // log.info("FileChangeListenerAdaptor#onStop");
    }
}
