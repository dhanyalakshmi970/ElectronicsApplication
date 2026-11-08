package com.Example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElectronicsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElectronicsApplication.class, args);

        System.out.println("Electronics Store Application Started Successfully!");
    }
}