package com.aroo.service;

import com.aroo.feign.TestFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ApiCallService {

    private final TestFeignClient feignClient;

    public String feign() {

        String param = "1";

        String result = feignClient.test(param);
        return result;

    }

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
}
