package org.event;

import lombok.Getter;

@Getter
public abstract class Event {
    private Object source;
    public Event(Object source){
        this.source = source;
    }

    @Override
    public String toString() {
        return "event: " + source.toString();
    }
}
