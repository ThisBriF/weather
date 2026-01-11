package hill.matt.weather.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import hill.matt.weather.model.visualcrossing.VisualCrossingWeatherApiResponse;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        JacksonJsonRedisSerializer<VisualCrossingWeatherApiResponse> serializer = new JacksonJsonRedisSerializer<>(
                VisualCrossingWeatherApiResponse.class);

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    }

}
