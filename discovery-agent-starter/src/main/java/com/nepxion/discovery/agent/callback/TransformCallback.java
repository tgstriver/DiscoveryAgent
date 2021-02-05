package com.nepxion.discovery.agent.callback;

import java.security.ProtectionDomain;

public interface TransformCallback {

    byte[] doInTransform(ClassLoader classLoader, String className,
                         Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                         byte[] classfileBuffer);
}