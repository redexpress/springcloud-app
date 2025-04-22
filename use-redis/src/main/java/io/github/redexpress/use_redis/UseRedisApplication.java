package io.github.redexpress.use_redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class UseRedisApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(UseRedisApplication.class, args);
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set("name", "YANG Gaofeng");
        String name = redisTemplate.opsForValue().get("name").toString();
        System.out.println(name);
    }
}
