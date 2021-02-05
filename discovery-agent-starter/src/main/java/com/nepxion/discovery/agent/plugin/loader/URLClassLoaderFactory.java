package com.nepxion.discovery.agent.plugin.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderFactory {

    public static ClassLoader createClassLoader(String name, URL[] urls, ClassLoader parent) {
        return new URLClassLoader(urls, parent);
    }
}