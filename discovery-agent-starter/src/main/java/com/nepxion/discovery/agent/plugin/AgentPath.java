package com.nepxion.discovery.agent.plugin;

import com.nepxion.discovery.agent.logger.AgentLogger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class AgentPath {

    private static final AgentLogger LOG = AgentLogger.getLogger(AgentPath.class.getName());
    private static File AGENT_PACKAGE_PATH;

    public static File getPath() {
        if (AGENT_PACKAGE_PATH == null) {
            AGENT_PACKAGE_PATH = findPath();
        }

        return AGENT_PACKAGE_PATH;
    }

    public static boolean isPathFound() {
        return AGENT_PACKAGE_PATH != null;
    }

    private static File findPath() {
        String classResourcePath = AgentPath.class.getName().replaceAll("\\.", "/") + ".class";
        URL resource = ClassLoader.getSystemClassLoader().getResource(classResourcePath);

        if (resource != null) {
            String urlString = resource.toString();

            LOG.info(String.format("The beacon class location is %s.", urlString));

            int insidePathIndex = urlString.indexOf('!');
            boolean isInJar = insidePathIndex > -1;

            if (isInJar) {
                urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
                File agentJarFile = null;
                try {
                    agentJarFile = new File(new URL(urlString).toURI());
                } catch (MalformedURLException | URISyntaxException e) {
                    LOG.warn("Can not locate agent jar file by url:" + urlString, e);
                }

                if (agentJarFile.exists()) {
                    return agentJarFile.getParentFile();
                }
            } else {
                int prefixLength = "file:".length();
                String classLocation = urlString.substring(prefixLength, urlString.length() - classResourcePath.length());

                return new File(classLocation);
            }
        }

        LOG.warn("Can not locate agent jar file");
        throw new RuntimeException("Can not locate agent jar file");
    }
}