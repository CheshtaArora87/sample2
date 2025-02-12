package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("CONFIGURATION-SERVICE")
public interface ServiceInterface {

    @GetMapping("/config/get")
    String getConfig(@RequestParam("config_name") String configName);

    @PostMapping("/config/post")
    ResponseEntity<String> setDefinitions(@RequestBody Map<String, Map<String,String>> data);


}



