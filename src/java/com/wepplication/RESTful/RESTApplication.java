package com.wepplication.RESTful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class })
public class RESTApplication {
    public static void main(String[] args) {
        SpringApplication.run(RESTApplication.class, args);
    }
}
