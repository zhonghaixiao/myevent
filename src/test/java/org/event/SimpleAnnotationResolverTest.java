package org.event;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleAnnotationResolverTest {

    @Test
    public void resolve() {
        List<EventListener> listeners = new SimpleAnnotationResolver().resolve(this).getListeners();
        assertEquals(2, listeners.size());
    }

    @Subscription
    public void comsume1(Event event){
        System.out.println();
    }


    public void comsume2(Event event){
        System.out.println();
    }

    @Subscription
    public void comsume3(Event event){
        System.out.println();
    }
}