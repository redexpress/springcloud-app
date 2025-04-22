package io.github.redexpress.user_mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MqConfig {
    public static final String QUEUE_NAME = "yang";
    public static final String EXCHANGE_NAME = "yang.exchange";
    public static final String ROUTING_KEY = "yang.routing.key";
    @Bean
    public Queue yangQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
