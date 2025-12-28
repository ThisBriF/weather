package hill.matt.weather.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import hill.matt.weather.config.WeatherApiConfig;
import hill.matt.weather.exception.WeatherClientException;
import hill.matt.weather.model.apiresponse.VisualCrossingWeatherApiResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisualCrossingWeatherApiService {

    @Qualifier("visualCrossingClient")
    private final RestClient restClient;
    private final WeatherApiConfig weatherApiConfig;

    @Cacheable("weatherCache")
    public VisualCrossingWeatherApiResponse retrieveWeatherForecastData(String latitude, String longitude) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{latitude},{longitude}")
                        .queryParam("key", weatherApiConfig.key())
                        .build(latitude, longitude))
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new WeatherClientException(
                            response.getStatusText(),
                            response.getStatusCode().value(),
                            new String(response.getBody().readAllBytes()) // Reads the actual content
                    );
                })
                .body(VisualCrossingWeatherApiResponse.class);
    }

}
