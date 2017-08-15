package com.kee.ad.config;

//import com.kee.ad.interceptor.LoginInterceptor;
import com.kee.ad.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author KeeYang on 2017/8/5.
 * @Description :
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

    @Value("${useSwagger}")
    protected boolean useSwagger;

    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("useSwagger:{}", useSwagger);
        if (useSwagger) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
    }
}
