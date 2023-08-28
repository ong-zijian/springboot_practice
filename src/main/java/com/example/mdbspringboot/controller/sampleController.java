package com.example.mdbspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mdbspringboot.model.sample;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class sampleController {

    private final Logger logger = LoggerFactory.getLogger(sampleController.class);

    private final ItemRepository itemRepository;

    public sampleController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get all items
    @GetMapping("/items")
    public List<sample> getAllItems() {
        logger.info("Fetching all items from the database.");
        List<sample> items = itemRepository.findAll();
        logger.info("Found {} items.", items.size());
        return items;
    }

    // Get specific item by name
    @GetMapping("/items/{name}")
    public ResponseEntity<sample> getItemByName(@PathVariable String name) {
        logger.info("Fetching item with name: {}", name);
        sample item = itemRepository.findItemByName(name);
        if (item != null) {
            logger.info("Found item: id={}, name={}, age={}, email={}", item.getId(), item.getName(), item.getAge(), item.getEmail());
            return ResponseEntity.ok(item);
        } else {
            logger.info("Item not found with name: {}", name);
            return ResponseEntity.notFound().build();
        }
    }

    // Post new item
    @PostMapping("/items")
    public ResponseEntity<sample> createItem(@RequestBody sample newItem) {
        logger.info("Creating a new item: {}", newItem);

        sample savedItem = itemRepository.save(newItem);
        logger.info("Item created with id: {}", savedItem.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // Delete an item
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        logger.info("Deleting item with id: {}", id);

        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            logger.info("Item deleted successfully.");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Item not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Update an item
    @PutMapping("/items/{id}")
    public ResponseEntity<sample> updateItem(@PathVariable String id, @RequestBody sample updatedItem) {
        logger.info("Updating item with id: {}", id);

        if (itemRepository.existsById(id)) {
            sample savedItem = itemRepository.save(updatedItem);
            logger.info("Item updated: {}", savedItem);
            return ResponseEntity.ok(savedItem);
        } else {
            logger.info("Item not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}

