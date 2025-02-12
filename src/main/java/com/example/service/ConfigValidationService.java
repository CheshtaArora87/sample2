package com.example.service;

import com.example.model.ConfigDefinition;
import com.example.model.ConfigFile;
import com.example.model.WarehouseProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigValidationService {



    private final Map<String, Map<String, String>> configDefinitionsMap = new HashMap<>();


    public void loadConfigDefinitions(String fileContent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ConfigFile configFile = objectMapper.readValue(fileContent, ConfigFile.class);



            for (ConfigDefinition config : configFile.getConfigDefinitions()) {
                Map<String, String> processMap = new HashMap<>();
                for (WarehouseProcess process : config.getWarehouseProcesses()) {
                    processMap.put(process.getWarehouseProcess(), process.getDefinition());
                }
                configDefinitionsMap.put(config.getConfigName(), processMap);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.json in Sample Service", e);
        }
        System.out.println(configDefinitionsMap);


    }



    public boolean isConfigValid(String configName) {
        return configDefinitionsMap.containsKey(configName);
    }
}
