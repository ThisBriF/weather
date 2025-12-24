package hill.matt.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "weatherapi")
public record WeatherApiConfig(String key) {}