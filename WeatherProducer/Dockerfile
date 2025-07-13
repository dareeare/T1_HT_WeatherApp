FROM eclipse-temurin:22-jdk AS build

WORKDIR /app
COPY . .

EXPOSE 8080

CMD ["java", "-jar", "target/WeatherProducer-0.0.1-SNAPSHOT.jar"]