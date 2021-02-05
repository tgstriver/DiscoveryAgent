package com.nepxion.discovery.agent.plugin;

import com.nepxion.discovery.agent.callback.TransformTemplate;
import com.nepxion.discovery.agent.loader.AgentClassLoader;
import com.nepxion.discovery.agent.logger.AgentLogger;
import com.nepxion.discovery.agent.matcher.ClassMatcher;
import com.nepxion.discovery.agent.matcher.MatcherFactory;
import com.nepxion.discovery.agent.threadlocal.ThreadLocalCopier;

import java.net.URLClassLoader;

public abstract class AbstractPlugin extends Plugin {

    private static final AgentLogger LOG = AgentLogger.getLogger(AbstractPlugin.class.getName());

    @Override
    public void install(TransformTemplate transformTemplate) {
        String matcherClassName = this.getMatcherClassName();
        Class<? extends AbstractPlugin> pluginClass = getClass();

        ClassMatcher classMatcher = MatcherFactory.newClassNameMatcher(matcherClassName);
        transformTemplate.transform(classMatcher, (classLoader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            try {
                String hookClassName = getHookClassName();
                ThreadLocalCopier.register(AgentClassLoader.load((URLClassLoader) pluginClass.getClassLoader(), classLoader, hookClassName));
            } catch (Exception e) {
                LOG.warn(String.format("Load %s error", className), e);
            }

            return null;
        });
    }

    /**
     * 返回存储ThreadLocal对象的类名，由于插件是可以插拔的，所以必须是字符串形式，不允许显式引入类
     *
     * @return
     */
    protected abstract String getMatcherClassName();

    /**
     * 返回ThreadLocalHook类名
     *
     * @return
     */
    protected abstract String getHookClassName();
}