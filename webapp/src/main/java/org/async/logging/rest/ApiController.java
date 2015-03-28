package org.async.logging.rest;

import org.apache.log4j.Logger;
import org.async.logging.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class ApiController {

    static Logger LOG = getLogger(ApiController.class);

    @Autowired
    private JmsTemplate template;

    @RequestMapping(value = "/greeting", method = GET)
    public @ResponseBody Customer index() {
        LOG.info("Greeting endpoint called!");
        return new Customer("Hello");
    }

    @RequestMapping(value = "/jms", method = GET)
    public void sendMessage() {
        LOG.info("Sending message!");
        template.send("mailbox-destination", session -> session.createTextMessage("Hello!"));
        LOG.info("Message sent!");
    }
}