package hill.matt.weather.config;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public class TimeCustomerDeserializer extends ValueDeserializer<OffsetTime> {

    @Override
    public OffsetTime deserialize(JsonParser p, DeserializationContext ctxt) {
        String timeString = p.getString();
        // Add default offset if none is present, e.g., UTC offset +00:00
        if (!timeString.contains("+") && !timeString.contains("-")) {
            timeString = timeString + "+00:00"; // assuming UTC if no offset is provided
        }
        return OffsetTime.parse(timeString, DateTimeFormatter.ISO_OFFSET_TIME);
    }

}
