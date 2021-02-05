package com.nepxion.discovery.agent.plugin.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginLoader {

    public static <T> List<T> load(ClassLoader classLoader, Class<T> clazz) {
        List<T> profilerPlugins = new ArrayList<>();
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz, classLoader);
        for (T t : serviceLoader) {
            profilerPlugins.add(t);
        }

        return profilerPlugins;
    }
}