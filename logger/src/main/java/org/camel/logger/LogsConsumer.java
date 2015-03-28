package org.camel.logger;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.main.Main;
import org.apache.log4j.Logger;

public class LogsConsumer {

    static Logger LOG = Logger.getLogger(LogsConsumer.class);

    public static void main(String[] args) throws Exception {
        LogsConsumer consumer = new LogsConsumer();
        consumer.boot();
    }

    public void boot() throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.bind("foo", new MyBean());
        main.addRouteBuilder(new MyRouteBuilder());
        CamelContext context = main.getOrCreateCamelContext();
        context.addComponent("activemq", activeMQComponentFactory());

        LOG.info("Starting camel. Use ctrl + c to terminate JVM");
        main.run();
    }

    private ActiveMQComponent activeMQComponentFactory() {
        ActiveMQConfiguration activeMQConfiguration = new ActiveMQConfiguration();
        activeMQConfiguration.setBrokerURL("tcp://localhost:61616");
        activeMQConfiguration.setUserName("admin");
        activeMQConfiguration.setPassword("admin");
        return new ActiveMQComponent(activeMQConfiguration);
    }
}