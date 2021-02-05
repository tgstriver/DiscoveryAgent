package com.nepxion.discovery.example.agent;

import com.nepxion.discovery.agent.threadlocal.AbstractThreadLocalHook;
import com.nepxion.discovery.example.sdk.MyContext;

import java.util.Map;

public class MyContextHook extends AbstractThreadLocalHook {

    @Override
    public Object create() {
        // 从主线程的ThreadLocal里获取并返回上下文对象
        return MyContext.getCurrentContext().getAttributes();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void before(Object object) {
        // 把create方法里获取到的上下文对象放置到子线程的ThreadLocal里
        if (object instanceof Map) {
            MyContext.getCurrentContext().setAttributes((Map<String, String>) object);
        }
    }

    @Override
    public void after() {
        // 线程结束，销毁上下文对象
        MyContext.clearCurrentContext();
    }
}