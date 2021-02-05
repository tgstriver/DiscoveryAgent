package com.nepxion.discovery.agent.async;

public class AsyncContext {

    private Object[] objects;

    public AsyncContext(Object[] objects) {
        this.objects = objects;
    }

    public Object[] getObjects() {
        return objects;
    }
}