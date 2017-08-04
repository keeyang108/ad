package com.kee.ad.config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author KeeYang on 2017/7/21.
 * @Description :
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan
public class SwaggerConfig {

    private boolean useSwagger = true;

    @Bean
    public Docket orderDocketBean() throws Exception {
        SwaggerConfigFactoryBean factoryBean = new SwaggerConfigFactoryBean("order", useSwagger, "com.kee.ad.controller");
        return factoryBean.getObject();
    }

}
