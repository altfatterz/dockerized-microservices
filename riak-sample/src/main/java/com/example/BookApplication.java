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
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner init() {
        return (args) -> bookRepository.save(
                "books", "mobydick", Book.builder()
                        .author("Herman Melville")
                        .title("Moby Dick")
                        .isbn("1111979723")
                        .body("Call me Ishmael. Some years ago...")
                        .copiesOwned(3)
                        .build()
        );
    }
}

