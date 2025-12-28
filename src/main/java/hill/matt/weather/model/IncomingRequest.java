package hill.matt.weather.model;

import jakarta.validation.constraints.NotEmpty;

public record IncomingRequest(
    @NotEmpty
    String latitude,
    @NotEmpty
    String longitude) {}
