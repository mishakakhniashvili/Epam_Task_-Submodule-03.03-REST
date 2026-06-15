package com.epam.gymcrm;

import com.epam.gymcrm.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class GymCrmApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.close();
    }
}