package jmaster.io.notificationservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationConfig {
    @Value("${spring.mail.username}")
    private String from;
}
