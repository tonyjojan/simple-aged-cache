package io.collective;

public class ExpirableEntry {
    private Object value;
    private long timeOfExpiry;

    public ExpirableEntry(Object value, long retention){
        this.value = value;
        this.timeOfExpiry = retention;
    }
    public Object getValue(){
        return this.value;
    }

    public long getTimeOfExpiry(){
        return timeOfExpiry;
    }

}
