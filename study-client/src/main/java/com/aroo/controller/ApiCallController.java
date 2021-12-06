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

        try {

            String apiURL = "http://localhost:8090/test";
            String data = "?param=1";
            apiURL += data;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            // post request

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error";
    }

    @GetMapping("/rest")
    public String rest(){
        String apiURL = "http://localhost:8090/test";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiURL)
                .queryParam("param", 1);


        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, entity, String.class );


        String result = responseEntity.getBody();

        return result;
    }


    @GetMapping("/feign")
    public String feign(){
        String result = apiCallService.feign();
        return result;
    }
}
