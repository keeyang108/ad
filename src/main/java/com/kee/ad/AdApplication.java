package com.kee.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.kee.ad"})
@ImportResource(locations = "classpath*:/spring/application-dao.xml")
public class AdApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }
}
