package com.demo.bag;

/**
 * @author 揭光智
 * @date 2019/04/16
 */
public class ArrayBag<T> implements Bag<T> {

    private static final int DEFAULT_SIZE = 10;

    private static final Object[] DEFAULT__EMPTY_ELEMENTDATA = new Object[DEFAULT_SIZE];

    private Object[] data;

    private int size;

    public ArrayBag() {
        this.data = DEFAULT__EMPTY_ELEMENTDATA;
    }

    public ArrayBag(int size) {
        if (size > 0) {
            data = new Object[size];
        } else if (size == 0) {
            data = DEFAULT__EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("非法的初始化大小: " + size);
        }
    }

    @Override
    public boolean add(T t) {
        if (ensureCapacity(size)) {
            data[size++] = t;
        }
        return ensureCapacity(size);
    }

    @Override
    public T remove(T t) {
        T remove = null;
        for (int i = 0; i < size; ++i) {
            if (data[i] == t) {
                if (i != data.length - 1) {
                    remove = (T) data[i];
                    data[i] = data[i + 1];
                }
                data[size] = null;
            }
        }
        return remove;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean ensureCapacity(int minCapacity) {
        minCapacity += 1;
        return minCapacity < data.length;
    }
}
