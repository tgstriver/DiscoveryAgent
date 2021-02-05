package com.nepxion.discovery.agent.plugin;

import com.nepxion.discovery.agent.callback.TransformTemplate;

public abstract class Plugin {

    public abstract void install(TransformTemplate transformTemplate);

    /**
     * 是否启用插件，默认启用
     *
     * @return
     */
    protected boolean isEnabled() {
        return true;
    }
}