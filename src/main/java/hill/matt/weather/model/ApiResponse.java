package hill.matt.weather.model;

import java.util.ArrayList;
import java.util.List;

import hill.matt.weather.model.apiresponse.Day;
import hill.matt.weather.model.apiresponse.VisualCrossingWeatherApiResponse;
import lombok.Data;

@Data
public class ApiResponse {

    private Double latitude;
    private Double longitude;
    private String resolvedAddress;
    private String timezone;

    private List<ForecastDays> days = new ArrayList<>();

    public ApiResponse(VisualCrossingWeatherApiResponse weatherResponse) {
        this.latitude = weatherResponse.getLatitude();
        this.longitude = weatherResponse.getLongitude();
        this.resolvedAddress = weatherResponse.getResolvedAddress();
        this.timezone = weatherResponse.getTimezone();

        for (Day day : weatherResponse.getDays()) {
            ForecastDays forecastDays = new ForecastDays();

            forecastDays.setDatetime(day.getDatetime());
            forecastDays.setTempmax(day.getTempmax());
            forecastDays.setTempmin(day.getTempmin());
            forecastDays.setTemp(day.getTemp());
            forecastDays.setCloudcover(day.getCloudcover());
            forecastDays.setConditions(day.getConditions());
            forecastDays.setHumidity(day.getHumidity());
            forecastDays.setPrecip(day.getPrecip());
            forecastDays.setPressure(day.getPressure());
            forecastDays.setSunrise(day.getSunrise());
            forecastDays.setSunset(day.getSunset());
            forecastDays.setWindspeed(day.getWindspeed());

            this.days.add(forecastDays);
        }
    }

}
