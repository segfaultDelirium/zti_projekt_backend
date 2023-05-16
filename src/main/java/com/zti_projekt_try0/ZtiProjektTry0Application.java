package com.zti_projekt_try0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ZtiProjektTry0Application {

    ApplicationContext context = new AnnotationConfigApplicationContext(ObjectMapperConfig.class);
    public static void main(String[] args) {
        SpringApplication.run(ZtiProjektTry0Application.class, args);
    }

}
