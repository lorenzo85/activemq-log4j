package org.camel.logger;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("activemq:topic:LOG.TOPIC_CAMEL").beanRef("foo");
    }
}
