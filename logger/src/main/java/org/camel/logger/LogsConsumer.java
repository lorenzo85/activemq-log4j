package org.camel.logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.camel.main.Main;

import javax.sql.DataSource;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class LogsConsumer {

    private static final String ACTIVEMQ = "activemq";
    private static final String DATA_SOURCE_REF = "dataSource";

    public static void main(String[] args) throws Exception {
        AppProperties properties = new AppProperties();
        LogsConsumer consumer = new LogsConsumer();
        consumer.boot(properties);
    }

    public void boot(AppProperties properties) throws Exception {
        Main main = new Main();
        main.bind(DATA_SOURCE_REF, dataSource(properties));
        main.bind(ACTIVEMQ, activeMQComponent(properties.getBrokerUrl()));
        main.addRouteBuilder(new LogsRoute(properties.getBrokerTopic(), DATA_SOURCE_REF));
        main.enableHangupSupport();
        main.run();
    }

    private static DataSource dataSource(AppProperties properties) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getDataSourceUrl());
        dataSource.setUser(properties.getDataSourceUser());
        dataSource.setPassword(properties.getDataSourcePassword());
        return dataSource;
    }
}