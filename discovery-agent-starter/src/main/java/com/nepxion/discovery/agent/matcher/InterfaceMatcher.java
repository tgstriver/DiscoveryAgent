package com.nepxion.discovery.agent.matcher;

import javassist.CtClass;

import com.nepxion.discovery.agent.util.ClassInfo;

public class InterfaceMatcher implements ClassMatcher {

    private final String interfaceName;

    public InterfaceMatcher(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public boolean match(ClassInfo classInfo) {
        try {
            CtClass ctClass = classInfo.getCtClass();
            CtClass[] interfaces = ctClass.getInterfaces();
            for (CtClass anInterface : interfaces) {
                if (interfaceName.equals(anInterface.getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}