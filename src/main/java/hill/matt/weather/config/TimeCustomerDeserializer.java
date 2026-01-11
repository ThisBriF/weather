package hill.matt.weather.config;

import java.io.IOException;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TimeCustomerDeserializer extends JsonDeserializer<OffsetTime> {

    @Override
    public OffsetTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String timeString = p.getText();
        // Add default offset if none is present, e.g., UTC offset +00:00
        if (!timeString.contains("+") && !timeString.contains("-")) {
            timeString = timeString + "+00:00"; // assuming UTC if no offset is provided
        }
        return OffsetTime.parse(timeString, DateTimeFormatter.ISO_OFFSET_TIME);
    }

}
