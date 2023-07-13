package com.example.lookalikecelebrity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@Slf4j
@EnableCaching
@EnableScheduling
@EnableJpaRepositories
public class LookAlikeCelebrityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookAlikeCelebrityApplication.class, args);
    }

}
