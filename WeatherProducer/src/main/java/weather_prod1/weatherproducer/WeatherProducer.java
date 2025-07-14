package weather_prod1.weatherproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class WeatherProducer {
    private final Logger log = Logger.getLogger(WeatherProducer.class.getName());
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
            log.info("Data was generated");
        } catch (Exception e) {
            log.severe("ERROR with generating data: ");
        }

    }
}
