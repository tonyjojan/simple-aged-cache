package io.collective;

public class ExpirableEntry {
    private Object value;
    private int timeOfExpiry;

    public ExpirableEntry(Object value, int retention){
        this.value = value;
        this.timeOfExpiry = retention;
    }
    public Object getValue(){
        return this.value;
    }

    public int getTimeOfExpiry(){
        return timeOfExpiry;
    }

}
