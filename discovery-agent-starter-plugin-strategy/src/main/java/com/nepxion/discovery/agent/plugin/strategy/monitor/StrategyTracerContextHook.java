package com.nepxion.discovery.agent.plugin.strategy.monitor;

import com.nepxion.discovery.agent.threadlocal.AbstractThreadLocalHook;
import com.nepxion.discovery.plugin.strategy.monitor.StrategyTracerContext;

public class StrategyTracerContextHook extends AbstractThreadLocalHook {

    @Override
    public Object create() {
        return StrategyTracerContext.getCurrentContext().getSpan();
    }

    @Override
    public void before(Object object) {
        StrategyTracerContext.getCurrentContext().setSpan(object);
    }

    @Override
    public void after() {
        StrategyTracerContext.clearCurrentContext();
    }
}