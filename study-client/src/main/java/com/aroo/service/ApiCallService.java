package com.aroo.service;

import com.aroo.feign.TestFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiCallService {

    private final TestFeignClient feignClient;

    public String feign() {

        String param = "1";

        String result = feignClient.test(param);
        return result;

    }
}
