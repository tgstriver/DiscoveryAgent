package com.nepxion.discovery.agent.matcher;

/**
 * 简单工厂模式
 */
public final class MatcherFactory {

    private MatcherFactory() {

    }

    public static ClassMatcher newClassNameMatcher(String classInternalName) {
        return new ClassNameMatcher(classInternalName);
    }

    public static MatcherOperator newPackageBasedMatcher(String basePackageName, String interfaceName) {
        return new MatcherOperator(new PackageMatcher(basePackageName), new InterfaceMatcher(interfaceName));
    }
}