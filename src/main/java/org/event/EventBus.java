package org.event;

public interface EventBus {
    void publish(Object event);
    void register(Object instance);
    void unregister(Object instance);
}
