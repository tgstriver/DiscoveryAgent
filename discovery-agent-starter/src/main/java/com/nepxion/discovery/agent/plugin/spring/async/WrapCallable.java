package com.nepxion.discovery.agent.plugin.spring.async;

import com.nepxion.discovery.agent.async.AsyncContext;
import com.nepxion.discovery.agent.async.AsyncContextAccessor;
import com.nepxion.discovery.agent.threadlocal.ThreadLocalCopier;

import java.util.concurrent.Callable;

public class WrapCallable<T> implements AsyncContextAccessor, Callable<T> {

    private Callable<T> callable;
    private AsyncContext asyncContext;

    public WrapCallable(Callable<T> callable) {
        this.callable = callable;
        Object[] objects = ThreadLocalCopier.create();
        this.setAsyncContext(new AsyncContext(objects));
    }

    @Override
    public T call() throws Exception {
        Object[] objects = asyncContext.getObjects();
        ThreadLocalCopier.before(objects);

        try {
            return callable.call();
        } finally {
            ThreadLocalCopier.after();
        }
    }

    @Override
    public void setAsyncContext(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return asyncContext;
    }
}