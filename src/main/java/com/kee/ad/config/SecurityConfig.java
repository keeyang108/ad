//package com.kee.ad.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
///**
// * @author KeeYang on 2017/8/3.
// * @Description :
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        // enable xss protection
//        httpSecurity.headers().xssProtection();
//        // disable cookie header: jsession
//        httpSecurity.sessionManagement().sessionFixation().none();
//        httpSecurity.csrf()
//                .disable()
//                .exceptionHandling()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/resources/**")
//                .permitAll()
//                .antMatchers("/public**")
//                .permitAll()
//                .antMatchers("/druid/**")
//                .permitAll()
//                .antMatchers("/common/public**")
//                .permitAll()
//
//                // swagger related
//                .antMatchers("/swagger-ui.html")
//                .permitAll()
//                .antMatchers("/swagger-resources/**")
//                .permitAll()
//                .antMatchers("/v2/api-docs/**")
//                .permitAll()
//                .antMatchers("/webjars/springfox-swagger-ui/**")
//                .permitAll()
//
//                .antMatchers("/front/order/append")
//                .permitAll()
//                // for login auth
//                .anyRequest()
//                .authenticated()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .httpBasic();
//
//        // disable page caching
//        httpSecurity.headers().cacheControl();
//    }
//}
