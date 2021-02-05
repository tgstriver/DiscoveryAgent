package com.nepxion.discovery.agent.plugin.strategy.mdc;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

public class DiscoveryMDCPlugin extends AbstractPlugin {

    private final Boolean threadMDCEnabled = Boolean.valueOf(System.getProperty("thread.mdc.enabled", "true"));

    @Override
    protected String getMatcherClassName() {
        return "org.slf4j.MDC";
    }

    @Override
    protected String getHookClassName() {
        return MDCContextHook.class.getName();
    }

    @Override
    protected boolean isEnabled() {
        return threadMDCEnabled;
    }
}