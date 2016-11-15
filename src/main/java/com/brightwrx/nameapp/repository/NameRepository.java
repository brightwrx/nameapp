package com.brightwrx.nameapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brightwrx.nameapp.model.Name;

public interface NameRepository extends CrudRepository<Name, Long> {

    public List<Name> findByFirstName(String firstName); 
}