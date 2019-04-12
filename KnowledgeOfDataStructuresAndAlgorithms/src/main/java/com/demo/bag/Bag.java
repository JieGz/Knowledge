package com.demo.bag;

/**
 * @author 揭光智
 * @date 2019/04/11
 */
public interface Bag<T> {

    /**
     * 向包中添加元素t
     *
     * @param t 元素t
     * @return 添加元素是否为空, 如果包满了, 添加元素就会失败
     */
    boolean add(T t);

    /**
     * 从包中移除元素t
     *
     * @param t 元素t
     * @return 返回被删除的元素, 如果包中不存想要被删除的元素, 则返回null
     */
    T remove(T t);

    /**
     * 返回包当前的大小
     *
     * @return 包的大小
     */
    int size();

    /**
     * 返回包是否为空
     *
     * @return 当包为空时, 返回true
     */
    boolean isEmpty();
}
