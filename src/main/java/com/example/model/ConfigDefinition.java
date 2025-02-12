package com.example.model;

import java.util.List;

public class ConfigDefinition {
    private String configName;
    private List<WarehouseProcess> warehouseProcesses;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public List<WarehouseProcess> getWarehouseProcesses() {
        return warehouseProcesses;
    }

    public void setWarehouseProcesses(List<WarehouseProcess> warehouseProcesses) {
        this.warehouseProcesses = warehouseProcesses;
    }


}

