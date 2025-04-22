package io.github.redexpress.order;

import io.github.redexpress.user.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "user")
//public interface UserClient {
//    @RequestMapping(value="/user", method = RequestMethod.GET)
//    public String user(@RequestParam("name") String name);
//}

@FeignClient(name = UserApi.SERVICE_NAME)
public interface UserClient extends UserApi {
}
