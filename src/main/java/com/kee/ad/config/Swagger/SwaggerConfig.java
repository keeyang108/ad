package com.kee.ad.config.Swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author KeeYang on 2017/7/21.
 * @Description :
 */
@Configuration
//@ComponentScan
public class SwaggerConfig {

    @Value("${useSwagger}")
    private boolean useSwagger ;

    @Bean
    public Docket orderDocketBean() throws Exception {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(setApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.kee.add.controller"))
////                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
        SwaggerConfigFactoryBean factoryBean = new SwaggerConfigFactoryBean("order", useSwagger, "com.kee.ad.controller");
        return factoryBean.getObject();
    }

    protected ApiInfo setApiInfo() {
        return new ApiInfoBuilder().title("Ad API 说明").description("hehe").version("1.0").build();
    }

}
