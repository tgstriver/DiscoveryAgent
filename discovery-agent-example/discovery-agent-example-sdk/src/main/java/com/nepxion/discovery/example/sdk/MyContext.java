package com.nepxion.discovery.example.sdk;

import java.util.HashMap;
import java.util.Map;

public class MyContext {

    private static final ThreadLocal<MyContext> THREAD_LOCAL = ThreadLocal.withInitial(MyContext::new);

    public static MyContext getCurrentContext() {
        return THREAD_LOCAL.get();
    }

    public static void clearCurrentContext() {
        THREAD_LOCAL.remove();
    }

    private Map<String, String> attributes = new HashMap<>();

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}