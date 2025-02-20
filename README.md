# Configuration-Connector



## Contents

> [Description](#description)

> [Dependencies](#dependencies) 

> [How to Run](#how)

> [Installation & Setup](#installation)

> [Logging & Debugging](#logging)



## Description
The Connector Library is designed to be added as a dependency to other microservices. It provides centralized validation of the configDefinitions provided by a service at their startup and publishes them to a RabbitMQ queue. This allows the Configuration Service to update and manage configuration definitions dynamically.

The library ensures that:
- Microservices register their configuration definitions on startup.
- Validation occurs before publishing to prevent missing or incorrect definitions.
- Configuration fetch requests are validated at runtime, ensuring that a service can only use a configName if it has provided a definition for it on startup.

## Dependancies
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

// To include it as a dependency in other service
<dependency>
	<groupId>com.addverb</groupId>
	<artifactId>connector</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>

```

## Installation and Setup

### `Prerequisites`

>**Java 17+**

>**Maven**

>**Docker & Docker Compose**

>**RabbitMQ**


## Logging & Debugging

- **`Trace-ID & Correlation-ID`** added for debugging.

- **`Structured logging`** using Spring Boot Logger.

- **`Logs`** stored in logs/connector.log.




