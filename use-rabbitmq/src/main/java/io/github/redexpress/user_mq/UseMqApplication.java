package io.github.redexpress.user_mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UseMqApplication implements CommandLineRunner {
    static Logger logger = LoggerFactory.getLogger(UseMqApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(UseMqApplication.class, args);
    }

    @Autowired
    AmqpTemplate mqTemplate;

    @Override
    public void run(String... args) throws Exception {
        final String message = "message from YANG";
        mqTemplate.convertAndSend(MqConfig.EXCHANGE_NAME, MqConfig.ROUTING_KEY, message);
        logger.info("message sent.");
    }
}
