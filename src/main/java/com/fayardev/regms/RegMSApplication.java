package com.fayardev.regms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RegMSApplication extends SpringBootServletInitializer {
    private static final Class<RegMSApplication> applicationClass = RegMSApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(RegMSApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
