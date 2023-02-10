package com.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntegrationsRestController {

    private final RestTemplate restTemplate;

    @RequestMapping("/home")
    public String home() {
        return "Hello world";
    }

    @GetMapping(value = "/callclienthome")
    private String getHomeClient() {
        String uri = "http://localhost:8080/home";
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping(value = "/repos")
    private List<Object> getUsersRepos(String username) {
        String uri = String.format("https://api.github.com/users/%s/repos", username);
        Object[] result = restTemplate.getForObject(uri, Object[].class);
        return result != null ? List.of(result) : null;
    }
}
