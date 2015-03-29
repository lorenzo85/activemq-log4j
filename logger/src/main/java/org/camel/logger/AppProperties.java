package org.camel.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final String PROPERTIES_FILE = "application.properties";

    private Properties properties = new Properties();

    public AppProperties() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE);
        properties.load(inputStream);
    }

    public String getDataSourcePassword() {
        return properties.getProperty("jdbc.password");
    }

    public String getDataSourceUser() {
        return properties.getProperty("jdbc.user");
    }

    public String getDataSourceUrl() {
        return properties.getProperty("jdbc.host");
    }

    public String getBrokerUrl() {
        return properties.getProperty("activemq.url");
    }

    public String getBrokerTopic() {
        return properties.getProperty("activemq.topic");
    }
}
