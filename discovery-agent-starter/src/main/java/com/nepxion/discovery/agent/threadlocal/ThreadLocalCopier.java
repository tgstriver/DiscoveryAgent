package com.nepxion.discovery.agent.threadlocal;

import com.nepxion.discovery.agent.logger.AgentLogger;

import java.util.ArrayList;
import java.util.List;

public final class ThreadLocalCopier {

    private ThreadLocalCopier() {

    }

    private static final AgentLogger LOG = AgentLogger.getLogger(ThreadLocalCopier.class.getName());
    private static List<ThreadLocalHook> threadHooks = new ArrayList<>();

    public static void register(ThreadLocalHook threadHook) {
        threadHooks.add(threadHook);
    }

    public static Object[] create() {
        Object[] objects = new Object[threadHooks.size()];
        try {
            for (int i = 0; i < objects.length; i++) {
                ThreadLocalHook threadHook = threadHooks.get(i);
                objects[i] = threadHook.create();
            }
        } catch (Exception e) {
            LOG.warn("Execute create(construct) method for thread hook error, message:", e);
        }

        return objects;
    }

    public static void before(Object[] objects) {
        try {
            for (int i = 0; i < objects.length; i++) {
                ThreadLocalHook threadHook = threadHooks.get(i);
                threadHook.before(objects[i]);
            }
        } catch (Exception e) {
            LOG.warn("Execute before(run/call) method for thread hook error, message:", e);
        }
    }

    public static void after() {
        try {
            for (ThreadLocalHook threadHook : threadHooks) {
                threadHook.after();
            }
        } catch (Exception e) {
            LOG.warn("Execute after(run/call) method for thread hook error, message:", e);
        }
    }
}