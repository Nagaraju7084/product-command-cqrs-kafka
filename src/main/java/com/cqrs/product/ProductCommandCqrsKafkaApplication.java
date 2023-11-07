package com.cqrs.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCommandCqrsKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCommandCqrsKafkaApplication.class, args);
    }

}
