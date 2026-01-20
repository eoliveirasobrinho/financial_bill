package com.itgen.financialit.config.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        // suporte a LocalDate, LocalDateTime, etc
        mapper.registerModule(new JavaTimeModule());

        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        // evita timestamps em datas
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
