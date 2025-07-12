package weather_prod1.weatherproducer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private String city;
    private LocalDate timestamp;
    private int temperature;
    private String condition;
}
