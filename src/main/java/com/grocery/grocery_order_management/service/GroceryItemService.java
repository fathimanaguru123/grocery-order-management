package com.grocery.grocery_order_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.grocery_order_management.entity.GroceryItem;
import com.grocery.grocery_order_management.repository.GroceryItemRepository;

@Service
public class GroceryItemService {
  @Autowired 
  private GroceryItemRepository groceryItemRepository;
  public GroceryItem saveGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    // Get all grocery items
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    // Get grocery item by ID
    public Optional<GroceryItem> getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id);
    }

    // Delete grocery item by ID
    public void deleteGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    public Optional<GroceryItem> updateGroceryItem(Long id, GroceryItem updatedItem) {

    return groceryItemRepository.findById(id)
            .map(existingItem -> {

                existingItem.setName(updatedItem.getName());
                existingItem.setCategory(updatedItem.getCategory());
                existingItem.setPrice(updatedItem.getPrice());
                existingItem.setQuantity(updatedItem.getQuantity());

                return groceryItemRepository.save(existingItem);
            });
}
}
