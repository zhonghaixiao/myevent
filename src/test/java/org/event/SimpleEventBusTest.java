package org.event;

import lombok.ToString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SimpleEventBusTest {

    @Test
    public void publish() {
        EventBus bus = new SimpleEventBus();
        bus.register(this);
        bus.publish(new SimpleEvent("event1"));
    }

    @Test
    public void register() {

    }

    @Test
    public void unregister() {

    }

    @Subscription
    public void consume(SimpleEvent event){
        System.out.println("consume: " + event);
    }

    @Subscription
    public void consume1(SimpleEvent1 event){
        System.out.println("consume1: " + event);
    }

    @Subscription
    public void consume2(SimpleEvent2 event){
        System.out.println("consume2: " + event);
    }

    @ToString
    class SimpleEvent extends Event{

        public SimpleEvent(Object source) {
            super(source);
        }
    }

    @ToString
    class SimpleEvent1 extends Event{

        public SimpleEvent1(Object source) {
            super(source);
        }
    }

    @ToString
    class SimpleEvent2 extends Event{

        public SimpleEvent2(Object source) {
            super(source);
        }
    }

}