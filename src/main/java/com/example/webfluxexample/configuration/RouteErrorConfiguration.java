package com.example.webfluxexample.configuration;

import org.springframework.boot.web.error.ErrorAttributeOptions;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Configuration
public class RouteErrorConfiguration {
    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
                errorAttributes.put("error-attributes", "Error in request");
                errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
                return errorAttributes;
            }
        };
    }
}
