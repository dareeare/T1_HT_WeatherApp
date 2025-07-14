package weather_prod1.weatherproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class RandomGenerateWeatherData implements GenerateWeatherData {
    private static final List<String> CITIES = Arrays.asList("Minsk", "Grodno", "Gomel", "Vitebsk", "Mogilev", "Brest");
    private static final List<String> CONDITIONS = Arrays.asList("Sunny", "Rainy", "Windy", "Cloudy", "Overcast", "Rainstorm");
    private final Random random = new Random();

    @Override
    public WeatherData getWeatherData() {
        String city = CITIES.get(random.nextInt(CITIES.size()));
        String condition = CONDITIONS.get(random.nextInt(CONDITIONS.size()));
        int temperature = random.nextInt(36);
        LocalDate localDate = LocalDate.now().minusDays(random.nextInt(7));
        WeatherData wd = new WeatherData(city, localDate, temperature, condition);
        log.info("New weather data generated: " + city + ", " + localDate.toString() + ", " + condition + ", " + temperature);
        return wd;
    }
}
