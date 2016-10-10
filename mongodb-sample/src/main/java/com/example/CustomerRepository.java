package com.example;

import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * @author Zoltan Altfatter
 */
public interface CustomerRepository extends Repository<Customer, Long> {

    Stream<Customer> findAll();

    void save(Customer customer);
}
