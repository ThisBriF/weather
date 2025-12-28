package hill.matt.weather.model.apiresponse;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Day {

    @JsonProperty("datetime")
    private LocalDate datetime;
    @JsonProperty("tempmax")
    private long tempmax;
    @JsonProperty("tempmin")
    private double tempmin;
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("precip")
    private long precip;
    @JsonProperty("windspeed")
    private double windspeed;
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("cloudcover")
    private double cloudcover;
    @JsonProperty("sunrise")
    private OffsetTime sunrise;
    @JsonProperty("sunset")
    private OffsetTime sunset;
    @JsonProperty("conditions")
    private String conditions;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("stations")
    private List<String> stations;
    @JsonProperty("hours")
    private List<Hour> hours;

}
