package com.nepxion.discovery.agent.matcher;

import com.nepxion.discovery.agent.util.ClassInfo;

public interface ClassMatcher {

    boolean match(ClassInfo classInfo);
}