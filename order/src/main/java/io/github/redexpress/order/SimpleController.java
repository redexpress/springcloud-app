package io.github.redexpress.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/")
    public String root() {
        return "{}";
    }

    @GetMapping("/info")
    public String info() {
        return "{\"name\":\"order\"}";
    }
}
