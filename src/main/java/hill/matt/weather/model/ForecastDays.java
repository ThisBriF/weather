package hill.matt.weather.model;

import java.time.LocalDate;
import java.time.OffsetTime;

import lombok.Data;

@Data
public class ForecastDays {

    private LocalDate datetime;
    private long tempmax;
    private double tempmin;
    private double temp;
    private double humidity;
    private long precip;
    private double windspeed;
    private double pressure;
    private double cloudcover;
    private OffsetTime sunrise;
    private OffsetTime sunset;
    private String conditions;

}
