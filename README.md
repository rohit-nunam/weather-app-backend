# Weather App

This project is a Spring Boot application that provides weather information based on a given pin code. The application exposes a RESTful endpoint to fetch the weather details, including temperature, humidity, and other weather parameters.

## Prerequisites

- Java 17
- Maven or Gradle
- Google Maps API Key
- OpenWeatherMap API Key

## Dependencies

The project uses the following dependencies:
- Spring Boot 3.2.5
- Spring Web
- Lombok
- RestTemplate
- JPA (Jakarta Persistence API)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/rohit-nunam/weather-app.git
cd weather-app
```

## Configuration

```bash
google.maps.api.key: your google maps api key 
openweathermap.api.key: your openweathermap api key
```

## Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

## Usage

```bash
req:
GET /api/v1/weather?pinCode=123456

{
  "description": "clear sky",
  "temp": 23.5,
  "feelsLike": 22.3,
  "tempMin": 20.0,
  "tempMax": 25.0,
  "pressure": 1012,
  "humidity": 60,
  "visibility": 10000,
  "windSpeed": 5.5,
  "name": "Sample City"
}


```