FROM eclipse-temurin:22-jdk AS build

WORKDIR /app
COPY . .

EXPOSE 8081

CMD ["java", "-jar", "target/WeatherConsumer-0.0.1-SNAPSHOT.jar"]