package weather_prod1.weatherproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherProducer {
    private final KafkaTemplate<String, WeatherData> kafkaTemplate;
    private final GenerateWeatherData dataGenerator;

    public WeatherProducer(KafkaTemplate<String, WeatherData> kafkaTemplate, GenerateWeatherData dataGenerator) {
        this.kafkaTemplate = kafkaTemplate;
        this.dataGenerator = dataGenerator;
    }
    @Scheduled(fixedRate = 2000) // Every 2 seconds
    public void generateWeatherData() {
        WeatherData wd = dataGenerator.getWeatherData();
        try {
            kafkaTemplate.send("weather-topic", wd.getCity(), wd);
            System.out.println("Produced weather data: " + wd);
        } catch (Exception e) {
            System.out.println("ERROR weather data: " + wd);
        }

    }
}
