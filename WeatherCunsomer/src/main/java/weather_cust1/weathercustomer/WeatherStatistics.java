package WeatherCunsomer.src.main.java.weather_cust1.weathercustomer;

import weather_prod1.weatherproducer.WeatherData;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class WeatherStatistics {
    private final Logger log = Logger.getLogger(WeatherStatistics.class.getName());
    private double totalTemp = 0;
    private int count = 0;
    private int minTemp = Integer.MAX_VALUE;
    private LocalDate minTempDate;
    private String minTempCity;
    private int maxTemp = Integer.MIN_VALUE;
    private LocalDate maxTempDate;
    private String maxTempCity;
    private int goodDays = 0;
    private int badDays = 0;
    private int consecutiveRainyDays = 0;
    private boolean lastDayRainy = false;
    private Set<LocalDate> processedDates = new HashSet<>();

    public void update(WeatherData data) {

        if (data.getTemperature() < minTemp) {
            minTemp = data.getTemperature();
            minTempDate = data.getTimestamp();
            minTempCity = data.getCity();
            log.info("New min temperature is " + minTemp + " in " + minTempCity);
        }

        if (data.getTemperature() > maxTemp) {
            maxTemp = data.getTemperature();
            maxTempDate = data.getTimestamp();
            maxTempCity = data.getCity();
            log.info("New max temperature is " + maxTemp + " in " + maxTempCity);
        }

        if (!processedDates.contains(data.getTimestamp())) {
            processedDates.add(data.getTimestamp());
            totalTemp += data.getTemperature();
            count++;

            if (data.getCondition().equals("Sunny")
                    || data.getCondition().equals("Cloudy")
                    || data.getCondition().equals("Overcast")) {
                goodDays++;
                log.info("New good day in " + data.getCity());
            }

            if (data.getCondition().equals("Rainy")
                    || data.getCondition().equals("Windy")
                    || data.getCondition().equals("Rainstorm")) {
                badDays++;
                log.info("New bad day in " + data.getCity());
            }

            if (count % 10 == 0) {
                log.info(getStatistics());
            }
        }
    }

    String getStatistics() {
        StringBuilder sb = new StringBuilder("=====STATISTICS=====\n");
        sb.append("Average temperature: " + (double)(totalTemp / count) + "\n");
        sb.append("Min temperature: " + minTemp + " - was " + minTempDate.toString() + " in " + minTempCity + "\n");
        sb.append("Max temperature: " + maxTemp + " - was " + maxTempDate.toString() + " in " + maxTempCity + "\n");
        sb.append("Good days: " + goodDays + "\n");
        sb.append("Bad days: " + badDays + "\n");
        sb.append("========END========\n");

        return sb.toString();
    }

}
