package com.nepxion.discovery.agent.matcher;

public class PackageMatcher {

    private String packageName;

    public PackageMatcher(String packageName) {
        this.packageName = packageName;
    }

    public boolean match(String className) {
        return className.startsWith(packageName);
    }
}