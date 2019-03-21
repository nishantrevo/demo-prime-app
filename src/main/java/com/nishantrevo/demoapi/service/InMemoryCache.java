package com.nishantrevo.demoapi.service;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ToString
@Service
public class InMemoryCache<K,V> implements CacheService<K,V>{

    private Map<K,V> map = new ConcurrentHashMap();

    @Override
    public boolean isCached(K key) {
        log.info("Checking Key[{}] is Cached", key);
        boolean found = map.containsKey(key);
        log.info(
                "Key[{}] is{} Cached"
                , key
                , found?"":" not");
        return found;
    }

    @Override
    public V addToCache(K key, V value) {
        log.info("Adding Key[{}] = Value[{}]", key, value);
        V oldValue = map.put(key,value);
        log.info("Previous value for Key[{}] = Value[{}]", key, oldValue);
        return oldValue;
    }

    @Override
    public V getCachedValue(Object key) {
        log.info("Getting {} from cache", key);
        V value = map.get(key);
        log.info("Returning Key[{}] Value=[{}] from cache", key, value);
        return value;
    }
}
