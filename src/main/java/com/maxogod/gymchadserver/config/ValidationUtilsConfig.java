package com.maxogod.gymchadserver.config;

import com.maxogod.gymchadserver.util.ValidationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationUtilsConfig {

    @Bean
    public ValidationUtils validationUtils() {
        return new ValidationUtils();
    }

}
