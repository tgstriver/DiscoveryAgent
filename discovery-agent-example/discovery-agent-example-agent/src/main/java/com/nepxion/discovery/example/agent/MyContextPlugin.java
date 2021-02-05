package com.nepxion.discovery.example.agent;

import com.nepxion.discovery.agent.plugin.AbstractPlugin;

public class MyContextPlugin extends AbstractPlugin {

    private final Boolean threadMyPluginEnabled = Boolean.valueOf(System.getProperty("thread.myplugin.enabled", "false"));

    @Override
    protected String getMatcherClassName() {
        // 返回存储ThreadLocal对象的类名，由于插件是可以插拔的，所以必须是字符串形式，不允许是显式引入类
        return "com.nepxion.discovery.example.sdk.MyContext";
    }

    @Override
    protected String getHookClassName() {
        // 返回ThreadLocalHook类名
        return MyContextHook.class.getName();
    }

    @Override
    protected boolean isEnabled() {
        // 通过外部-Dthread.myplugin.enabled=true/false的运行参数来控制当前Plugin是否生效。该方法在父类中定义的返回值为true，即缺省为生效
        return threadMyPluginEnabled;
    }
}