package org.event;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class SimpleAnnotationResolver implements Resolver {

    private List<EventListener> listeners;

    public SimpleAnnotationResolver(){
        listeners = new ArrayList<>();
    }

    public List<EventListener> getListeners(){
        return listeners;
    }

    public Resolver resolve(Object instance) {
        Class clazz = instance.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            Parameter[] parameters = method.getParameters();
            Class[] paramTypes = method.getParameterTypes();
            if (parameters.length != 1){
                System.out.println("method " + method.getName() + " has more than one arguments !!");
            }else {
                if (Event.class.isAssignableFrom(paramTypes[0])){
                    Subscription annotation = method.getAnnotation(Subscription.class);
                    if (annotation != null) {
                        SimpleAnotationListener listener = new SimpleAnotationListener(instance, method, paramTypes[0]);
                        listeners.add(listener);
                    }
                }else{
                    System.out.println("method parameter type is not a Event!!!!");
                }
            }

        }
        return this;
    }

}
