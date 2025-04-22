package io.github.redexpress.order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.redexpress.user.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallUserController {
    static Logger log = LoggerFactory.getLogger(CallUserController.class);

    @Autowired
    @Qualifier("rest1")
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("rest2")
    RestTemplate restTemplate2;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    UserClient userClient;

    @GetMapping("/hello")
    public String hello(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("user");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()
                + "/user?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/hello2")
    @HystrixCommand(fallbackMethod = "fallback")
    public String hello2(String name) {
        String url = "http://user/user?name=" + name;
        return restTemplate2.getForObject(url, String.class);
    }

    @GetMapping("/hello3")
    public String hello3(String name) {
        return userClient.user(name);
    }

    @GetMapping("/hello4")
    public String hello4(String name) {
        return userClient.user(name);
    }

    public String fallback(String name, Throwable throwable) {
        log.error("Fallbacked: {}", throwable);
        log.warn("restTemplate, name: {}", name);
        return "restTemplate call user service" + name;
    }
}
