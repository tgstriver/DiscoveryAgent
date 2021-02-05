package com.nepxion.discovery.agent.plugin.strategy.monitor;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

public class DiscoveryMonitorPlugin extends AbstractPlugin {

    private final Boolean threadMonitorEnabled = Boolean.valueOf(System.getProperty("thread.monitor.enabled", "true"));

    @Override
    protected String getMatcherClassName() {
        return "com.nepxion.discovery.plugin.strategy.monitor.StrategyTracerContext";
    }

    @Override
    protected String getHookClassName() {
        return StrategyTracerContextHook.class.getName();
    }

    @Override
    protected boolean isEnabled() {
        return threadMonitorEnabled;
    }
}