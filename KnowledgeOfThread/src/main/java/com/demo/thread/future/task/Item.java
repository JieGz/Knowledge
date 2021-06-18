package com.demo.thread.future.task;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 商品
 */
@Data
@RequiredArgsConstructor
public class Item {
    final String name;
    int remaining = 1000;
    @ToString.Exclude
    ReentrantLock lock = new ReentrantLock();
}