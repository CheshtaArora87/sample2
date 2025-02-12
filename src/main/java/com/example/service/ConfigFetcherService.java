package com.example.service;

import com.example.feign.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigFetcherService {


    @Autowired
    private  ServiceInterface serviceInterface;

    @Autowired
    private ConfigValidationService configValidationService;



    public String fetchConfig(String configName) {
        if (!configValidationService.isConfigValid(configName)) {
            throw new IllegalArgumentException("Invalid config name: " + configName + ". Not found in config.json");
        }
        return serviceInterface.getConfig(configName);
    }
}

