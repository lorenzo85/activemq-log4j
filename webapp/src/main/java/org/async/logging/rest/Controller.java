package org.async.logging.rest;

import org.apache.log4j.Logger;
import org.async.logging.model.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.*;
import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class Controller {

    static Logger LOG = getLogger(Controller.class);

    @RequestMapping(value = "/greeting", method = GET)
    public @ResponseBody Customer index(@RequestParam("name") String name) {
        Customer customer = new Customer(name);
        LOG.info(format("CREATED: %s", customer));
        return customer;
    }
}