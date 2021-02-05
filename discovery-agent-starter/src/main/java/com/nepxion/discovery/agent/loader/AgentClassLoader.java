package com.nepxion.discovery.agent.loader;

import java.net.URLClassLoader;

public class AgentClassLoader {

    @SuppressWarnings("unchecked")
    public static <T> T load(URLClassLoader currentClassLoader, ClassLoader targetClassLoader, String className)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        URLClassLoader urlClassLoader = new URLClassLoader(currentClassLoader.getURLs(), targetClassLoader);
        Object object = Class.forName(className, true, urlClassLoader).newInstance();
        return (T) object;
    }
}