package com.nepxion.discovery.agent.transformer;

import com.nepxion.discovery.agent.callback.TransformCallback;
import com.nepxion.discovery.agent.callback.TransformTemplate;
import com.nepxion.discovery.agent.matcher.ClassMatcher;
import com.nepxion.discovery.agent.util.ClassUtil;
import com.nepxion.discovery.agent.util.StringUtil;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DispatcherClassFileTransformer implements ClassFileTransformer {

    private TransformTemplate transformTemplate;

    public DispatcherClassFileTransformer(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (StringUtil.isEmpty(className)) {
            return new byte[]{};
        }

        String clazzName = ClassUtil.toInternalName(className);
        ClassMatcher classMatcher = transformTemplate.findClassMatcher(clazzName);

        if (null == classMatcher) {
            classMatcher = transformTemplate.findInterfaceMatcher(clazzName, loader, classfileBuffer);
        }

        if (null != classMatcher) {
            TransformCallback transformCallback = transformTemplate.findTransformCallback(classMatcher);
            if (null != transformCallback) {
                return transformCallback.doInTransform(loader, clazzName, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        }

        return new byte[]{};
    }
}