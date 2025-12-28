package hill.matt.weather.model.apiresponse;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisualCrossingWeatherApiResponse {

    @JsonProperty("queryCost")
    private long queryCost;
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("resolvedAddress")
    private String resolvedAddress;
    @JsonProperty("address")
    private String address;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("tzoffset")
    private long tzoffset;
    @JsonProperty("days")
    private List<Day> days;
    @JsonProperty("stations")
    private Map<String, Station> stations;

}
