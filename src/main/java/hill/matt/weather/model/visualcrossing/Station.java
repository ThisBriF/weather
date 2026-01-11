package hill.matt.weather.model.visualcrossing;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Station {

    @JsonProperty("name")
    private String name;
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("distance")
    private long distance;

}
