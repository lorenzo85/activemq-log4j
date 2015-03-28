package org.async.logging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "logTopic", subscription = "logTopic", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

    }
}
