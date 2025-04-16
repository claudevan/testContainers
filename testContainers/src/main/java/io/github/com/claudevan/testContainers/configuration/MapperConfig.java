package io.github.com.claudevan.testcontainers.configuration;

import io.github.com.claudevan.testcontainers.mappers.CepMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CepMapper cepMapper() {
        return Mappers.getMapper(CepMapper.class);
    }
}
