package com.aroo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "test-api", url = "http://localhost:8090")
public interface TestFeignClient {

    @RequestMapping (method = RequestMethod.GET, value = "/test")
    String test(@RequestParam(name = "param") String param);

}
