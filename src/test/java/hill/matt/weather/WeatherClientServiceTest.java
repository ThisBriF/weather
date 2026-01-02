package hill.matt.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import hill.matt.weather.model.ApiResponse;
import hill.matt.weather.model.apiresponse.VisualCrossingWeatherApiResponse;
import hill.matt.weather.service.VisualCrossingWeatherApiService;
import hill.matt.weather.service.WeatherClientService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientServiceTest {

    @Mock
    private VisualCrossingWeatherApiService visualCrossingWeatherApiService;

    @InjectMocks
    private WeatherClientService weatherClientService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void retrieveWeatherForecast_shouldReturnApiResponse_whenJsonIsValid() throws IOException {
        // Arrange
        ClassPathResource resource = new ClassPathResource("sample-response.json");
        VisualCrossingWeatherApiResponse mockResponse = objectMapper.readValue(resource.getInputStream(),
                VisualCrossingWeatherApiResponse.class);

        when(visualCrossingWeatherApiService.retrieveWeatherForecastData(anyString(), anyString()))
                .thenReturn(mockResponse);

        // Act
        ApiResponse response = weatherClientService.retrieveWeatherForecast("51.5074", "0.1278");

        // Assert
        assertNotNull(response);
        assertEquals("Reston, VA, United States", response.getResolvedAddress());
        assertEquals("Partially cloudy", response.getDays().get(0).getConditions());
        verify(visualCrossingWeatherApiService).retrieveWeatherForecastData("51.5074", "0.1278");
    }
}
