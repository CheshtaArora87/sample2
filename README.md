# Configuration-Connector


## Contents

> [Description](#description)

> [Dependencies](#dependencies) 

> [How to Run](#how-to-run)

> [Installation & Setup](#installation--setup)

> [Configuration & Integration](Configuration--Integration)

> [Logging & Debugging](#logging--debugging)



## Description
The Connector Library is designed to be added as a dependency to other microservices. It provides centralized validation of the configDefinitions provided by a service at their startup and publishes them to a RabbitMQ queue. This allows the Configuration Service to update and manage configuration definitions dynamically.

### Features
- Automatic Validation: Ensures that services provide correctly structured configuration definitions.
- RabbitMQ Integration: Publishes validated configurations to RabbitMQ for dynamic updates.
- Runtime Validation: Ensures that services use only those configurations they have registered.
- Logging & Debugging: Includes structured logging with Trace-ID & Correlation-ID for better tracking.

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

// Build the project
mvn clean install

// Run using Docker Compose
docker-compose up -d

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
On startup, your service should send the content of its config.json to the Connector Library, which validates it and publishes it to RabbitMQ.

## Configuration & Integration
The config.json file should follow this structure:
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
When a service attempts to fetch a configuration, the Connector Library checks if it had registered at least one process and definition for that configuration at startup. If not, the request is rejected.

## Logging & Debugging

- **`Trace-ID & Correlation-ID`** added for debugging.

- **`Structured logging`** using Spring Boot Logger.

- **`Logs`** stored in logs/connector.log.




