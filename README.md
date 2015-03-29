# log4j and JMS appender demo

Sends log4j logs to an ActiveMQ topic. A standalone camel component listens for messages on the topic and stores received logs in a database.

Usage: curl -X GET 'http://localhost:8080/greeting?name=Mark!'
