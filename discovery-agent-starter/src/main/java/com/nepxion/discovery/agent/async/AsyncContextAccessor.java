package com.nepxion.discovery.agent.async;

public interface AsyncContextAccessor {

    void setAsyncContext(AsyncContext asyncContext);

    AsyncContext getAsyncContext();
}