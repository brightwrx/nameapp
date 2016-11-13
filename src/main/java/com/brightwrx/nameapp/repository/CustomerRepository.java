package com.brightwrx.nameapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brightwrx.nameapp.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public List<Customer> findByFirstName(String firstName); 
}