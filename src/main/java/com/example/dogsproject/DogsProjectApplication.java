package com.example.dogsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DogsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogsProjectApplication.class, args);
    }

}
