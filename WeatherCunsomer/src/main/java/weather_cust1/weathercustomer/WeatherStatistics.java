package WeatherCustomer.src.main.java.weather_cust1.weathercustomer;

import weather_prod1.weatherproducer.WeatherData;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class WeatherStatistics {
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

    void update(WeatherData data) {

        if (data.getTemperature() < minTemp) {
            minTemp = data.getTemperature();
            minTempDate = data.getTimestamp();
            minTempCity = data.getCity();
        }

        if (data.getTemperature() > maxTemp) {
            maxTemp = data.getTemperature();
            maxTempDate = data.getTimestamp();
            maxTempCity = data.getCity();
        }

        if (!processedDates.contains(data.getTimestamp())) {
            processedDates.add(data.getTimestamp());
            totalTemp += data.getTemperature();
            count++;

            if (data.getCondition().equals("Sunny")
                    || data.getCondition().equals("Cloudy")
                    || data.getCondition().equals("Overcast")) {
                goodDays++;
            }

            if (data.getCondition().equals("Rainy")
                    || data.getCondition().equals("Windy")
                    || data.getCondition().equals("Rainstorm")) {
                badDays++;

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

        return sb.toString();
    }

}
