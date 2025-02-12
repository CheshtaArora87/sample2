package com.example.model;

import java.util.List;

public class ConfigFile {
    private List<ConfigDefinition> configDefinitions;

    public List<ConfigDefinition> getConfigDefinitions() {
        return configDefinitions;
    }

    public void setConfigDefinitions(List<ConfigDefinition> configDefinitions) {
        this.configDefinitions = configDefinitions;
    }
}
