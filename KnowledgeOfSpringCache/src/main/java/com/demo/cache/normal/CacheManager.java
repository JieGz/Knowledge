package com.demo.cache.normal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
public class CacheManager<T> {

    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T getValue(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    public void evictCache(String key) {
        cache.remove(key);
    }

    public void evictCache() {
        cache.clear();
    }
}
