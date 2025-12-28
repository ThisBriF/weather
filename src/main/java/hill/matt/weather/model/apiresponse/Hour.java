package hill.matt.weather.model.apiresponse;

import java.time.OffsetTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Hour {

    @JsonProperty("datetime")
    private OffsetTime datetime;
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("dew")
    private Double dew;
    @JsonProperty("windspeed")
    private double windspeed;
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("cloudcover")
    private Long cloudcover;
    @JsonProperty("conditions")
    private String conditions;
    @JsonProperty("icon")
    private String icon;

}
