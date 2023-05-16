package com.zti_projekt_try0.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Before("execution(* com.zti_projekt_try0.Location.LocationController.getCurrentLocations() )")
    public void Logger(){
        System.out.println("Loggers");
    }
}
