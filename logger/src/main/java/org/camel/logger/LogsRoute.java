package org.camel.logger;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.spi.LoggingEvent;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.*;

public class LogsRoute extends RouteBuilder {

    private String logTopic;
    private String dataSourceName;

    public LogsRoute(String logTopic, String dataSourceName) {
        this.logTopic = logTopic;
        this.dataSourceName = dataSourceName;
    }

    @Override
    public void configure() {
        from(format("activemq:topic:%s", logTopic))
                .process(exchange -> {
                    LoggingEvent log = exchange.getIn().getBody(LoggingEvent.class);
                    Map<String, Object> map = new HashMap<>();
                    map.put("message", log.getMessage());
                    map.put("level", log.getLevel().toString());
                    map.put("logger", log.getLogger().getName());
                    exchange.getIn().setBody(map);
                })
                .to(format("sql:insert into log (message, level, logger) values (:#message, :#level, :#logger)?dataSource=%s", dataSourceName));
    }
}
