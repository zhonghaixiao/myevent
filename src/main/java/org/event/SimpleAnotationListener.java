package org.event;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ToString
@Getter
@Data
public class SimpleAnotationListener implements EventListener {

    private Object target;
    private Method method;
    private Class type;

    public SimpleAnotationListener(Object target, Method method, Class type){
        this.target = target;
        this.method = method;
        this.type = type;
    }

    public SimpleAnotationListener(EventListener listener){

    }

    public void onEvent(Event event) {
        try {
            method.invoke(target, event);
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
