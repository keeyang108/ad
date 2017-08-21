package com.kee.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages = {"com.kee.ad"})
@ImportResource(locations = "classpath*:/spring/application-dao.xml")
public class AdApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }
}
