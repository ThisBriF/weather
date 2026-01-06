# Weather Service

A Spring Boot demo project for weather services.

## Overview

This application provides a RESTful API built with Spring Boot. It includes integrated features for caching, object mapping, validation, and live API documentation.

## Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **API Documentation**: SpringDoc (OpenAPI 3)
- **Object Mapping**: MapStruct
- **Caching**: Caffeine (In-memory)
- **Boilerplate**: Lombok

## Getting Started

### Prerequisites

- Java 17 SDK installed.

### Building the Project

This project uses the Maven Wrapper, so you don't need Maven installed globally.

```bash
./mvnw clean install
```

### Running the Application

You can start the application using the Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

Once started, the server defaults to port `8080`.

## API Documentation

The application exposes interactive API documentation via Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs

## Development Profiles

### `sync-bruno`

This project includes a special Maven profile named `sync-bruno`. It is designed to generate the `openapi.json` file without running the full test suite, which is useful for syncing API definitions with tools like Bruno.

To generate the `openapi.json` file in the `target/` directory:

```bash
./mvnw verify -Psync-bruno
```

## Features

- **Actuator**: Provides production-ready features such as health checks and metrics.
- **Validation**: Jakarta Bean Validation is enabled for request input validation.
- **Caching**: High-performance in-memory caching is configured using Caffeine.
