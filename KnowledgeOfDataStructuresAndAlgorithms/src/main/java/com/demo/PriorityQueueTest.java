package com.demo;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        System.out.println(7/2);

        Queue<Integer> queue = new PriorityQueue<>();
        Integer element = queue.peek();
        System.out.println("element:" + element + " size:{}" + queue.size());

        boolean add = queue.add(1);
        add = queue.offer(2);
        add = queue.offer(3);

        element = queue.element();
        System.out.println("element:" + element + " size:{}" + queue.size());
        element = queue.peek();
        System.out.println("peek:" + element + " size:{}" + queue.size());

        Integer remove = queue.remove();
        System.out.println("remove:" + remove + " size:{}" + queue.size());
        Integer poll = queue.poll();
        System.out.println("poll:" + poll + " size:{}" + queue.size());
        poll = queue.poll();
        System.out.println("poll:" + poll + " size:{}" + queue.size());
        poll = queue.poll();
        System.out.println("poll:" + poll + " size:{}" + queue.size());
        remove = queue.remove();
        System.out.println("remove:" + remove + " size:{}" + queue.size());
    }
}
