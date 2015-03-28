package org.camel.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class MyBean {
    static Logger LOG = Logger.getLogger(MyBean.class);

    public void onLogReceived(LoggingEvent event) {
        LOG.info("Received logging event: " + event.getMessage());
    }
}
