package com.example;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final SpringApplication s = new SpringApplication(Application.class);
        s.setBannerMode(Banner.Mode.OFF);
        s.run(args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
        return builder.build();
    }
}
