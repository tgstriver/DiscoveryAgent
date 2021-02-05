package com.nepxion.discovery.agent.plugin.thread.interceptor;

import com.nepxion.discovery.agent.async.AsyncContext;
import com.nepxion.discovery.agent.async.AsyncContextAccessor;
import com.nepxion.discovery.agent.threadlocal.ThreadLocalCopier;

public class ThreadConstructorInterceptor {

    public static void before(Object object) {
        if (object instanceof AsyncContextAccessor) {
            AsyncContextAccessor asyncContextAccessor = (AsyncContextAccessor) object;
            Object[] objects = ThreadLocalCopier.create();
            asyncContextAccessor.setAsyncContext(new AsyncContext(objects));
        }
    }
}