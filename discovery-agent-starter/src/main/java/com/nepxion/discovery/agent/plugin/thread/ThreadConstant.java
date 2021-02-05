package com.nepxion.discovery.agent.plugin.thread;

import com.nepxion.discovery.agent.plugin.thread.interceptor.ThreadCallInterceptor;
import com.nepxion.discovery.agent.plugin.thread.interceptor.ThreadConstructorInterceptor;

public interface ThreadConstant {

    String THREAD_SCAN_PACKAGES = "thread.scan.packages";
    String THREAD_REQUEST_DECORATOR_ENABLED = "thread.request.decorator.enabled";
    String RUNNABLE_CLASS_NAME = "java.lang.Runnable";
    String CALLABLE_CLASS_NAME = "java.util.concurrent.Callable";

    String THREAD_SCAN_PACKAGES_DELIMITERS = ";";
    String CONSTRUCTOR_INTERCEPTOR = String.format("%s.before(this);%n", ThreadConstructorInterceptor.class.getName());
    String RUN_BEFORE_INTERCEPTOR = String.format("%s.before(this);%n", ThreadCallInterceptor.class.getName());
    String RUN_AFTER_INTERCEPTOR = String.format("%s.after(this);%n", ThreadCallInterceptor.class.getName());
}