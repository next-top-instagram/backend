//package com.spring.instagram;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;

//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setExposedHeaders(List.of("*"));
//
//        // You can customize the following part based on your project, it's only a sample
//        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
//                .authenticated().and().csrf().disable().cors().configurationSource(request -> corsConfiguration);
//
//    }
//}