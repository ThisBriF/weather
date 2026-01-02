package hill.matt.weather;

import hill.matt.weather.controller.WeatherApiController;
import hill.matt.weather.exception.GlobalExceptionHandler;
import hill.matt.weather.exception.WeatherClientException;
import hill.matt.weather.service.WeatherClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherApiController.class)
@Import(GlobalExceptionHandler.class) // Explicitly import the advice to ensure it's loaded in the test slice
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherClientService weatherService;

    @Test
    void shouldHandleBadRequest_WhenUpstreamReturns400() throws Exception {
        // Given
        WeatherClientException exception = new WeatherClientException("Bad Request", 400, "Invalid coordinates");
        when(weatherService.retrieveWeatherForecast(any(), any())).thenThrow(exception);

        // When/Then
        mockMvc.perform(get("/getweather")
                        .param("latitude", "52.52")
                        .param("longitude", "13.40"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Weather Provider Error"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Bad Request"))
                .andExpect(jsonPath("$.upstreamStatusCode").value(400));
    }

    @Test
    void shouldHandleUnauthorized_WhenUpstreamReturns401() throws Exception {
        // Given
        WeatherClientException exception = new WeatherClientException("Unauthorized", 401, "Invalid API Key");
        when(weatherService.retrieveWeatherForecast(any(), any())).thenThrow(exception);

        // When/Then
        mockMvc.perform(get("/getweather")
                        .param("latitude", "52.52")
                        .param("longitude", "13.40"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.upstreamStatusCode").value(401));
    }

    @Test
    void shouldHandleNotFound_WhenUpstreamReturns404() throws Exception {
        // Given
        WeatherClientException exception = new WeatherClientException("Not Found", 404, "Location not found");
        when(weatherService.retrieveWeatherForecast(any(), any())).thenThrow(exception);

        // When/Then
        mockMvc.perform(get("/getweather")
                        .param("latitude", "0")
                        .param("longitude", "0"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.upstreamStatusCode").value(404));
    }

    @Test
    void shouldHandleGenericError_WhenUpstreamReturnsUnhandledCode() throws Exception {
        // Given
        // 503 is not explicitly handled in the switch case, so it should default to 500 Internal Server Error
        WeatherClientException exception = new WeatherClientException("Service Unavailable", 503, "Maintenance");
        when(weatherService.retrieveWeatherForecast(any(), any())).thenThrow(exception);

        // When/Then
        mockMvc.perform(get("/getweather")
                        .param("latitude", "52.52")
                        .param("longitude", "13.40"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.title").value("Weather Provider Error"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.upstreamStatusCode").value(503));
    }

    @Test
    void shouldReturnBadRequest_WhenValidationFails_MissingParams() throws Exception {
        // When/Then
        mockMvc.perform(get("/getweather"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_WhenValidationFails_EmptyParams() throws Exception {
        // When/Then
        mockMvc.perform(get("/getweather")
                        .param("latitude", "")
                        .param("longitude", ""))
                .andExpect(status().isBadRequest());
    }
    
}
