package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zoltan Altfatter
 */
@RestController
public class CustomerRestController {

    private CustomerRepository repository;

    public CustomerRestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/")
    public List<Customer> findAll() {
        return repository.findAll().collect(Collectors.toList());
    }
}
