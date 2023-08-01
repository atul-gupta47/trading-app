# Trading Application (trading-app)

## Description

The Trading Application repository contains an algorithmic trading application built in Java with the Spring Boot framework. This application is designed to execute various trading algorithms provided by the Trading Algo library based on incoming signals received through an HTTP endpoint.

---

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contact](#contact)

## Introduction

The Trading Application is a Java-based project that leverages Spring Boot to facilitate algorithmic trading using the Trading Algo library. It provides a RESTful API that allows users to submit signals for processing and executing the appropriate trading strategies.

## Technologies

- Java 17
- Spring Boot
- Gradle
- JUnit 5
- Mockito

## Features

- Signal processing and algorithmic trading execution.
- Integration with the Trading Algo library for various trading strategies.

## Getting Started

### Prerequisites

- Java 17: Ensure that you have Java 17 installed on your system.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/atul-gupta47/trading-app.git.git
   ```

2. Configure the HTTP port (default - 9001):

    - Open `application.properties` file in `src/main/resources`.
    - Update the property 'http' with new port number

3. Build the project:

   ```bash
   ./gradlew build
   ```

## Usage

To use the Trading Application, follow the installation steps mentioned above. Once the application is up and running, you can access the API endpoints to submit trading signals and execute the trading strategies.

## API Endpoints

- **Process Signal**:
    - Method: `GET`
    - URL: `/trading-app/v1/process-signal/{signalId}`
    - Description: Submits a trading signal for processing and executing the corresponding trading algorithm.
    - Request Parameters: `signalId` (integer) - The unique identifier for the trading signal.
    - Response: `HTTP Status 204 No Content` if the signal is successfully processed.

## Testing

The project includes unit tests to ensure the correctness and reliability of the trading algorithms and API endpoints. You can run the tests using the following command:

```bash
./gradlew test
```

## Contact

For any questions or feedback related to the project, you can reach out to the project maintainer at [atul.gupta090192@gmail.com
](mailto:atul.gupta090192@gmail.com
). You can also visit the project repository at [https://github.com/atul-gupta47/trading-app.git](https://github.com/atul-gupta47/trading-app.git).