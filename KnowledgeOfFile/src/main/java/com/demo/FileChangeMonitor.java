package com.demo;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author jieguangzhi
 * @date 2022-07-12
 */
@Slf4j
public class FileChangeMonitor implements Runnable {

    private static final FileChangeObserver[] EMPTY_ARRAY = {};

    private final long interval;

    private final List<FileChangeObserver> observers = new CopyOnWriteArrayList<>();

    private Thread thread;

    private volatile boolean running;

    public FileChangeMonitor() {
        this(1000L);
    }

    public FileChangeMonitor(long interval) {
        this.interval = interval;
    }

    public FileChangeMonitor(final long interval, final Collection<FileChangeObserver> observers) {
        // @formatter:off
        this(interval,
                Optional
                        .ofNullable(observers)
                        .orElse(Collections.emptyList())
                        .toArray(EMPTY_ARRAY)
        );
        // @formatter:on
    }

    public FileChangeMonitor(final long interval, final FileChangeObserver... observers) {
        this(interval);
        if (observers != null) {
            for (final FileChangeObserver observer : observers) {
                addObserver(observer);
            }
        }
    }

    /**
     * Returns the interval.
     *
     * @return the interval
     */
    public long getInterval() {
        return interval;
    }

    public void addObserver(final FileChangeObserver observer) {
        if (Objects.nonNull(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(final FileChangeObserver observer) {
        if (observer != null) {
            while (observers.remove(observer)) {
                // empty
            }
        }
    }

    public Iterable<FileChangeObserver> getObservers() {
        return observers;
    }


    public synchronized void start() throws Exception {
        if (running) {
            throw new IllegalStateException("Monitor is already running");
        }

        for (FileChangeObserver observer : observers) {
            observer.initialize();
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() throws Exception {
        stop(interval);
    }

    public synchronized void stop(final long stopInterval) throws Exception {
        if (!running) {
            throw new IllegalStateException("Monitor is not running");
        }
        running = false;
        try {
            thread.interrupt();
            thread.join(stopInterval);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (FileChangeObserver observer : observers) {
            observer.destroy();
        }
    }


    @Override
    public void run() {
        while (running) {
            for (FileChangeObserver observer : observers) {
                Stopwatch started = Stopwatch.createStarted();
                observer.checkAndNotify();
                log.info("检查和通知目录及文件的修改一共花费:{}ms", started.elapsed(TimeUnit.MILLISECONDS));
            }
            if (!running) {
                break;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(interval);
            } catch (final InterruptedException ignored) {
                // ignore
            }
        }
    }
}
