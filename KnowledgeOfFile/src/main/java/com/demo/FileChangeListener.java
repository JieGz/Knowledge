package com.demo;

import java.io.File;

/**
 * @author jieguangzhi
 * @date 2022-07-04
 */
public interface FileChangeListener {


    /**
     * File system observer started checking event.
     *
     * @param observer The file system observer
     */
    void onStart(final FileChangeObserver observer);

    /**
     * Directory created Event.
     *
     * @param directory The directory created
     */
    void onDirectoryCreate(final File directory);

    /**
     * Directory changed Event.
     *
     * @param directory The directory changed
     */
    void onDirectoryChange(final File directory);

    /**
     * Remove directory monitor.
     *
     * @param directory The directory monitor
     */
    void removeDirectoryMonitor(final File directory);

    /**
     * Directory deleted Event.
     *
     * @param directory The directory deleted
     */
    void onDirectoryDelete(final File directory);

    /**
     * File created Event.
     *
     * @param file The file created
     */
    void onFileCreate(final File file);

    /**
     * File changed Event.
     *
     * @param file The file changed
     */
    void onFileChange(final File file);

    /**
     * File remove monitor.
     *
     * @param file The file remove monitor
     */
    void removeFileMonitor(final File file);

    /**
     * File deleted Event.
     *
     * @param file The file deleted
     */
    void onFileDelete(final File file);

    /**
     * File system observer finished checking event.
     *
     * @param observer The file system observer
     */
    void onStop(final FileChangeObserver observer);

}
