package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.sample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<sample, String> {
    sample findItemByName(String name);

    // No need for a custom query to fetch all items
    // Spring Data provides a default findAll() method
    // that retrieves all documents from the collection

    // You can also add more custom methods if needed

}