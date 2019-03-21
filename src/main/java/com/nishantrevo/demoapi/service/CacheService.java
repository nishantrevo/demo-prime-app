package com.nishantrevo.demoapi.service;

public interface CacheService<K,V> {

    boolean isCached(K key);
    V addToCache(K key, V value);
    V getCachedValue(K key);

}
