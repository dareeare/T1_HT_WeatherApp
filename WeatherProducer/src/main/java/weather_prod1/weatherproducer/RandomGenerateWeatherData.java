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
        WeatherData wd = new WeatherData(
                CITIES.get(random.nextInt(CITIES.size())),
                LocalDate.now().minusDays(random.nextInt(7)),
                random.nextInt(36),
                CONDITIONS.get(random.nextInt(CONDITIONS.size())));
        return wd;
    }
}
