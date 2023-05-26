package com.example.ecommerce.webconfig;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class webConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/css/**", "/js/**", "/images/**")
                .addResourceLocations("classpath:/static/css", "classpath:/static/js/", "classpath:/static/images/");
    }
}
