package io.github.redexpress.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    public static final String SERVICE_NAME = "user";
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String user(@RequestParam("name") String name);
}