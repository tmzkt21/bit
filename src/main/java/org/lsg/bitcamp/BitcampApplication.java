package org.lsg.bitcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BitcampApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcampApplication.class, args);
    }

}
