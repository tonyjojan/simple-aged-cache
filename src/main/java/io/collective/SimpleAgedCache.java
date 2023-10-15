package io.collective;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
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
        clearCache();

        //add retention time to current time and set that in entry
        long retention;
        if(this.cacheClock == null){
            retention = 0;
        } else {
            retention = this.cacheClock.millis() + (long)retentionInMillis;
        }
        ExpirableEntry newExpirableEntry = new ExpirableEntry(value, retention);
        cacheMap.put(key, newExpirableEntry);
    }

    public boolean isEmpty() {
        return this.cacheMap.isEmpty();
    }

    public int size() {
        clearCache();
        return this.cacheMap.size();
    }

    public Object get(Object key) {
        ExpirableEntry currentValue = cacheMap.get(key);

        if (currentValue == null){
            return null;
        } else {
            return currentValue.getValue();
        }
    }

    public void clearCache(){
        if(this.cacheClock == null){
            //no clock during constructor, so perform without time based properties
            return;
        } else{
            for(Object currentKey : this.cacheMap.keySet()){
                ExpirableEntry currentEntry = this.cacheMap.get(currentKey);
                if(this.cacheClock.millis() >= currentEntry.getTimeOfExpiry()){
                    //current time is past expiry, so evict
                    this.cacheMap.remove(currentKey);
                }
            }
        }
    }
}