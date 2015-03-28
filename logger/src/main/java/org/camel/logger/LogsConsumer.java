package org.camel.logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import java.util.Date;

public class LogsConsumer {

    private Main main;

    public static void main(String[] args) throws Exception {
        LogsConsumer consumer = new LogsConsumer();
        consumer.boot();
    }

    public void boot() throws Exception {
        main = new Main();
        main.enableHangupSupport();
        main.bind("foo", new MyBean());
        main.addRouteBuilder(new MyRouteBuilder());

        // Run until terminate JVM
        System.out.println("Starting camel. Use ctrl + c to terminate JVM");
        main.run();
    }

    private static class MyRouteBuilder extends RouteBuilder {
        @Override
        public void configure() {
            from("timer:foo?delay=2000")
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            System.out.println("Invoked timer at " + new Date());
                        }
                    }).beanRef("foo");
        }
    }

    static class MyBean {
        public void callMe() {
            System.out.println("MyBean.callMe method has been called.");
        }
    }
}
