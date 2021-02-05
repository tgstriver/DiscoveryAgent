package com.nepxion.discovery.agent.matcher;

import com.nepxion.discovery.agent.util.ClassInfo;

public class ClassNameMatcher implements ClassMatcher {

    private final String className;

    protected ClassNameMatcher(String className) {
        this.className = className;
    }

    @Override
    public boolean match(ClassInfo classInfo) {
        return this.className.equals(classInfo.getClassName());
    }

    public String getClassName() {
        return className;
    }
}