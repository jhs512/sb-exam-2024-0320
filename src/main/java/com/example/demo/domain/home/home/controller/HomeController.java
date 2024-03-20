package com.example.demo.domain.home.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/callApi1")
    @ResponseBody
    public Map callApi1() {
        WebClient client = WebClient.builder()
                .baseUrl("http://apis.data.go.kr")
                .build();

        String serviceKey = "";
        String numOfRows = "50";

        Map rs = client
                .get()
                .uri(
                        uriBuilder -> uriBuilder.path("/6310000/petFriendlyFacilities/getPetFriendlyFacilitiesList")
                                .queryParam("serviceKey", serviceKey)
                                .queryParam("numOfRows", numOfRows)
                                .build()
                )
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return rs;
    }
}
