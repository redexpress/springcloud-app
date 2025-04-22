package io.github.redexpress.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
    @Bean(name = "rest1")
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }

    @Bean(name = "rest2")
    @LoadBalanced
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }
}
