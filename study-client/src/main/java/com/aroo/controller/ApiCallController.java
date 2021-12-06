package com.aroo.controller;

import com.aroo.service.ApiCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class ApiCallController {

    private final ApiCallService apiCallService;

    @GetMapping("/http")
    public String http(){

        return apiCallService.http();
    }

    @GetMapping("/rest")
    public String rest(){
        return apiCallService.rest();
    }


    @GetMapping("/feign")
    public String feign(){
        String result = apiCallService.feign();
        return result;
    }
}
