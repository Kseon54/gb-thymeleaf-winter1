package ru.gb.rest.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.gb.rest.service.ManufacturerGateway;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
@EnableFeignClients(basePackageClasses = {ManufacturerGateway.class})
public class ShopConfig {

    @Bean
    public AuditorAware<String> auditorAwareBean() {
        return () -> Optional.of("User");
    }
}
