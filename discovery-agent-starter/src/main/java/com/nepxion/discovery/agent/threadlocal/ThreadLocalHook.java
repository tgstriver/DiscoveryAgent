package com.nepxion.discovery.agent.threadlocal;

public interface ThreadLocalHook {

    /**
     * 从主线程的ThreadLocal里获取并返回上下文对象
     *
     * @return
     */
    Object create();

    /**
     * 把create方法里获取到的上下文对象放置到子线程的ThreadLocal里
     *
     * @param object
     */
    void before(Object object);

    /**
     * 线程结束，销毁上下文对象
     */
    void after();
}