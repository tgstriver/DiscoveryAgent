package com.nepxion.discovery.agent.plugin.strategy.gateway;

import org.springframework.web.server.ServerWebExchange;

import com.nepxion.discovery.agent.threadlocal.AbstractThreadLocalHook;
import com.nepxion.discovery.plugin.strategy.gateway.context.GatewayStrategyContext;

public class GatewayStrategyContextHook extends AbstractThreadLocalHook {

    @Override
    public Object create() {
        return GatewayStrategyContext.getCurrentContext().getExchange();
    }

    @Override
    public void before(Object object) {
        if (object instanceof ServerWebExchange) {
            GatewayStrategyContext.getCurrentContext().setExchange((ServerWebExchange) object);
        }
    }

    @Override
    public void after() {
        GatewayStrategyContext.clearCurrentContext();
    }
}