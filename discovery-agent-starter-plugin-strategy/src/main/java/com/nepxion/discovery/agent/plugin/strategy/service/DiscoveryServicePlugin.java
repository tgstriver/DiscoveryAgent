package com.nepxion.discovery.agent.plugin.strategy.service;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

/**
 * 服务端策略Header输出到异步子线程。默认开启
 */
public class DiscoveryServicePlugin extends AbstractPlugin {

    private final Boolean threadServiceEnabled = Boolean.valueOf(System.getProperty("thread.service.enabled", "true"));

    @Override
    protected String getMatcherClassName() {
        return "com.nepxion.discovery.plugin.strategy.service.context.RestStrategyContext";
    }

    @Override
    protected String getHookClassName() {
        return ServiceStrategyContextHook.class.getName();
    }

    /**
     * 通过外部-Dthread.service.enabled=true/false的运行参数来控制当前Plugin是否生效。该方法在父类中定义的返回值为true，即缺省为生效
     *
     * @return
     */
    @Override
    protected boolean isEnabled() {
        return threadServiceEnabled;
    }
}