package com.nepxion.discovery.agent.plugin.strategy.zuul;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

public class DiscoveryZuulPlugin extends AbstractPlugin {

    private final Boolean threadZuulEnabled = Boolean.valueOf(System.getProperty("thread.zuul.enabled", "true"));

    @Override
    protected String getMatcherClassName() {
        return "com.nepxion.discovery.plugin.strategy.zuul.context.ZuulStrategyContext";
    }

    @Override
    protected String getHookClassName() {
        return ZuulStrategyContextHook.class.getName();
    }

    @Override
    protected boolean isEnabled() {
        return threadZuulEnabled;
    }
}