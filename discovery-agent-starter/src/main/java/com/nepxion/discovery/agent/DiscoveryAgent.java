package com.nepxion.discovery.agent;

import com.nepxion.discovery.agent.callback.TransformTemplate;
import com.nepxion.discovery.agent.logger.AgentLogger;
import com.nepxion.discovery.agent.plugin.PluginFinder;
import com.nepxion.discovery.agent.transformer.DispatcherClassFileTransformer;

import java.lang.instrument.Instrumentation;

public class DiscoveryAgent {

    private static final AgentLogger LOG = AgentLogger.getLogger(DiscoveryAgent.class.getName());

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        LOG.info(String.format("%s agent on load, version %s", DiscoveryAgent.class.getSimpleName(), DiscoveryAgentConstant.DISCOVERY_AGENT_VERSION));

        TransformTemplate transformTemplate = new TransformTemplate();
        PluginFinder.load(transformTemplate);

        instrumentation.addTransformer(new DispatcherClassFileTransformer(transformTemplate));

        System.setProperty(DiscoveryAgentConstant.SPRING_APPLICATION_DISCOVERY_AGENT_VERSION, DiscoveryAgentConstant.DISCOVERY_AGENT_VERSION);
    }
}