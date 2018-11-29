package org.event;

import java.util.List;

public interface Resolver {
    Resolver resolve(Object instance);
    List<EventListener> getListeners();
}
