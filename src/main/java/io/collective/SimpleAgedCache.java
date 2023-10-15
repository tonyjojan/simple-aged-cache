package io.collective;

import java.time.Clock;
import java.util.HashMap;

public class SimpleAgedCache {

    private HashMap<Object, ExpirableEntry> cacheMap;
    private Clock cacheClock;
    public SimpleAgedCache(Clock clock) {
        this.cacheClock = clock;
        this.cacheMap = new HashMap<Object, ExpirableEntry>();
    }

    public SimpleAgedCache() {
        this.cacheMap = new HashMap<Object, ExpirableEntry>();
    }

    public void put(Object key, Object value, int retentionInMillis) {
        ExpirableEntry newExpirableEntry = new ExpirableEntry(value, retentionInMillis);
        cacheMap.put(key, newExpirableEntry);
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public Object get(Object key) {
        ExpirableEntry currentValue = cacheMap.get(key);

        if (currentValue == null){
            return null;
        } else{
            return currentValue.getValue();
        }
    }
}