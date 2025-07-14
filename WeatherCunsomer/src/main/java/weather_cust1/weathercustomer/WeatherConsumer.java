package WeatherCunsomer.src.main.java.weather_cust1.weathercustomer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import weather_prod1.weatherproducer.WeatherData;
import WeatherCunsomer.src.main.java.weather_cust1.weathercustomer.WeatherStatistics;

import java.util.logging.Logger;

@Component
public class WeatherConsumer {
    private final Logger log = Logger.getLogger(WeatherConsumer.class.getName());
    private final WeatherStatistics statistics;

    public WeatherConsumer(WeatherStatistics statistics) {
        this.statistics = statistics;
    }

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(WeatherData data) {
        if (data == null || data.getCity() == null) {
            log.info("An empty message was received");
            return;
        }

        try {
            log.info("Data was received: " + data);
            statistics.update(data);
        } catch (Exception e) {
            //System.out.printf("[ERROR]: %s%n", e.getMessage());
            log.severe("Data processing error");
        }
    }
}
