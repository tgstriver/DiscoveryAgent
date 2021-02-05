package com.nepxion.discovery.agent.plugin.strategy.mdc;

import com.nepxion.discovery.agent.threadlocal.AbstractThreadLocalHook;
import org.slf4j.MDC;

import java.util.Map;

public class MDCContextHook extends AbstractThreadLocalHook {

    @Override
    public Object create() {
        return MDC.getCopyOfContextMap();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void before(Object object) {
        if (object instanceof Map) {
            MDC.setContextMap((Map<String, String>) object);
        }
    }

    @Override
    public void after() {
        MDC.clear();
    }
}