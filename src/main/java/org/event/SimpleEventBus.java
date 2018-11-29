package org.event;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleEventBus implements EventBus {

    private Map<Object, Subscription> registry;
    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;
    private Resolver resolver;

    public SimpleEventBus(){
        registry = new LinkedHashMap<>();
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        resolver = new SimpleAnnotationResolver();
    }

    public void publish(Event event) {
        try{
            readLock.lock();
            for (Subscription s: registry.values()){
                s.onEvent(event);
            }
        }finally {
            readLock.unlock();
        }
    }

    public void register(Object instance) {
        try {
            writeLock.lock();

            //1. unregister
            unregister(instance);

            //2. 获取instance中所有带有@subscription的方法
            List<EventListener> listeners = resolver.resolve(instance).getListeners();
            Subscription subscription = new Subscription(listeners);
            registry.put(instance, subscription);


        }finally {
            writeLock.unlock();
        }



    }

    public void unregister(Object instance) {
        try{
            writeLock.lock();
            registry.remove(instance);
        }finally {
            writeLock.unlock();
        }
    }

    class Subscription {

        private List<EventListener> listeners;

        public Subscription(List<EventListener> listeners){
            this.listeners = listeners;
        }

        void onEvent(Event event){
            try{
                readLock.lock();
                for (EventListener listener : listeners) {
                    if (!(listener instanceof SimpleAnotationListener)){
                        listener = new SimpleAnotationListener(listener);
                    }
                    if (((SimpleAnotationListener)listener).getType().isAssignableFrom(event.getClass())){
                        listener.onEvent(event);
                    }
                }
            }finally {
                readLock.unlock();
            }
        }

    }

}
















