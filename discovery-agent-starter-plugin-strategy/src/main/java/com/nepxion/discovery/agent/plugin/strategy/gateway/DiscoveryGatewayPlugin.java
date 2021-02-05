package com.nepxion.discovery.agent.plugin.strategy.gateway;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

public class DiscoveryGatewayPlugin extends AbstractPlugin {

    private final Boolean threadGatewayEnabled = Boolean.valueOf(System.getProperty("thread.gateway.enabled", "true"));

    @Override
    protected String getMatcherClassName() {
        return "com.nepxion.discovery.plugin.strategy.gateway.context.GatewayStrategyContext";
    }

    @Override
    protected String getHookClassName() {
        return GatewayStrategyContextHook.class.getName();
    }

    @Override
    protected boolean isEnabled() {
        return threadGatewayEnabled;
    }
}