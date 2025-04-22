package io.github.redexpress.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class UserController implements UserApi{
    @GetMapping("/user")
    public String user(@RequestParam(required = false, defaultValue="Guest") String name){
        return "{\"user\": \"" + name + "\", \"version\": 2}";
    }
}
