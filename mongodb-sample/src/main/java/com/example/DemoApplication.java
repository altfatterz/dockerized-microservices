package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Zoltan Altfatter
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public CommandLineRunner init() {
        return (args) -> {
            customerRepository.save(new Customer("Han", "Solo"));
        };
    }

}
