package WeatherCunsomer.src.main.java.weather_cust1.weathercustomer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WeatherStatistics weatherStatistics() {
        return new WeatherStatistics();
    }
}
