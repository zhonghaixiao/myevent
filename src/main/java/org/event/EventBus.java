package org.event;

public interface EventBus {
    void publish(Event event);
    void register(Object instance);
    void unregister(Object instance);
}
