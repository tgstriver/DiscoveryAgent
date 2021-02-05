package com.nepxion.discovery.agent.plugin.strategy.zuul;

import com.nepxion.discovery.agent.threadlocal.AbstractThreadLocalHook;
import com.nepxion.discovery.plugin.strategy.zuul.context.ZuulStrategyContext;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ZuulStrategyContextHook extends AbstractThreadLocalHook {

    @Override
    public Object create() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        Map<String, String> headers = RequestContext.getCurrentContext().getZuulRequestHeaders();
        return new Object[]{request, headers};
    }

    @SuppressWarnings("unchecked")
    @Override
    public void before(Object object) {
        if (object.getClass().isArray()) {
            Object[] objects = (Object[]) object;

            if (objects[0] instanceof HttpServletRequest) {
                ZuulStrategyContext.getCurrentContext().setRequest((HttpServletRequest) objects[0]);
            }

            if (objects[1] instanceof Map) {
                ZuulStrategyContext.getCurrentContext().setHeaders((Map<String, String>) objects[1]);
            }
        }
    }

    @Override
    public void after() {
        ZuulStrategyContext.clearCurrentContext();
    }
}