package com.demo.design.pattern.simplefactory;

/**
 * @author 揭光智
 * @date 2019/02/13
 */
public abstract class AbstractHumanFactory {

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T extends Human> T createHuman(Class<T> clazz);
}
