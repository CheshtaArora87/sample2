package com.example.controller;

import com.example.service.ConfigFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private ConfigFetcherService configFetcherService;



    @GetMapping("/fetch-config")
    public String fetchConfig(@RequestParam String configName) {
        return configFetcherService.fetchConfig(configName);
    }
}
