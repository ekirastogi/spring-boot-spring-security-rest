package com.ekiras.ss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ekansh
 * @since 5/4/16
 */
@Configuration
public class ResourcesConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
         registry.addResourceHandler("/**")
            .addResourceLocations("/")
            .setCachePeriod(0);
    }

}
