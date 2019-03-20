package com.demo.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author 揭光智
 * @date 2019/03/20
 */
public class TestUnsafe {
    private static Unsafe unsafe;
    static final long stateOffset;
    private volatile long state = 0;

    static {
        try {
            //通过反射绕开AppClassLoader类加载器
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        TestUnsafe test = new TestUnsafe();
        boolean aLong = unsafe.compareAndSwapLong(test, stateOffset, 0, 1);
        System.out.println(aLong);
    }
}
