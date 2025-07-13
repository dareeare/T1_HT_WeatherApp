package WeatherCustomer.src.main.java.weather_cust1.weathercustomer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import weather_prod1.weatherproducer.WeatherData;

@Component
public class WeatherConsumer {
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
            return;
        }

        try {
            statistics.update(data);
        } catch (Exception e) {
            System.out.printf("[ERROR]: %s%n", e.getMessage());
        }
    }
}
