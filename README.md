# Configuration-Connector


## Contents

> [Description](#description)

> [Validation](#validation)

> [Dependencies](#dependencies) 

> [How to Run](#how-to-run)

> [Installation & Setup](#installation--setup)

> [Configuration & Integration](Configuration--Integration)

> [Logging & Debugging](#logging--debugging)



## Description
The Connector Library provides a mechanism for microservices to validate and manage configurations at startup. It automatically scans the config.json file of the service, performs validation, and publishes configuration definitions to a RabbitMQ queue.


### Features

- Automatic Validation: The library scans the content of config.json of service during the startup of service and ensures that the service provide correctly structured configuration definitions.
- Enum-Based Configuration Validation: Scans all enum classes implementing ConfigChecker and ensures all enum values exist in config.json. If any are missing, the service build and startup fail.
- RabbitMQ Integration: Publishes validated configuration definitions to RabbitMQ for dynamic updates.
- Logging & Debugging: Includes structured logging with Trace-ID & Correlation-ID for better tracking.

### Validation

#### Load config.json

- Reads config.json at startup.
- Ensures configDefinitions are present and not empty.
- Validates that each configuration has a configName and at least one warehouseProcess.
- Checks that each warehouseProcess has a processName and a definition.

#### Validate Enum Configurations

- Scans the package com.addverb for classes implementing ConfigChecker.
- Ensures that only enums implement ConfigChecker.
- Checks if all values from these enums exist in config.json.

#### Publishing Configuration Definitions to RabbitMQ queue

- Sends the validated configuration definitions to a RabbitMQ queue using ConfigPublisher.
- If publishing fails, the service startup is also halted.

#### Failure Handling

- If any validation step fails, an error is logged.
- The service build fails, preventing startup.
- Detailed failure messages are provided in logs1. .


## Dependencies
- `Java` - 17
- `springboot` - 3.4.2
- `RabbitMQ`
- `slf4j`: 2.0.16

## How to Run

```
//Clone the repository

git clone https://gitlab.addverb.com/addverb/capstone2k25/.git

cd configuration-connector 

// Build the project so that it can be used as dependency
mvn clean install

// Run locally using Spring Boot
mvn spring-boot:run

```

## Installation & Setup

### `Prerequisites`

>**Java 17+**

>**Maven**

>**Docker & Docker Compose**

>**RabbitMQ**

### To use the Connector Library in your service, add the following Maven dependency to your pom.xml:
```
<dependency>
    <groupId>com.addverb</groupId>
    <artifactId>connector</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
### Additionally, add the following Maven plugin to ensure validation occurs before the service starts:
```
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.1.0</version>
    <executions>
        <execution>
            <phase>compile</phase>  
            <goals>
                <goal>java</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <mainClass>com.addverb.connector.ConnectorApplication</mainClass>
        <classpathScope>runtime</classpathScope>
    </configuration>
</plugin>
```

## Configuration & Integration
The config.json file should be placed in src/main/resources and should follow this structure:
```
{
  "configDefinitions": [
    {
      "configName": "INVENTORY_DASHBOARD_CONFIGURATION",
      "warehouseProcesses": [
        {
          "warehouseProcess": "Cycle count",
          "definition": "To show visibility of the updated inventory"
        },
        {
          "warehouseProcess": "Stock adjustment",
          "definition": "To reflect manual inventory corrections"
        }
      ]
    }
  ]
}

```
### Implementing Enums for Configuration Fetching

To fetch any configuration, you need to create enum classes that implement the ConfigChecker interface, as the fetchConfig function only accepts objects of that type.


## Logging & Debugging

- Generates a correlation ID for tracking the entire configuration validation process.

- Generates a trace ID for individual fetchConfig() requests.

- Uses SLF4J for structured logging and troubleshooting.

