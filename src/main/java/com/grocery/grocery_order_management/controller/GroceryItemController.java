package com.grocery.grocery_order_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.grocery_order_management.entity.GroceryItem;
import com.grocery.grocery_order_management.service.GroceryItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {
  private final GroceryItemService groceryItemService;
  public GroceryItemController(GroceryItemService groceryItemService){
    this.groceryItemService = groceryItemService;
  }
  @PostMapping
    public ResponseEntity<GroceryItem> createGroceryItem(
            @Valid @RequestBody GroceryItem groceryItem) {

        GroceryItem savedItem =
                groceryItemService.saveGroceryItem(groceryItem);

        return ResponseEntity.ok(savedItem);
    }

    // Get all grocery items
    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        return ResponseEntity.ok(
                groceryItemService.getAllGroceryItems());
    }

    // Get grocery item by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(
            @PathVariable Long id) {

        return groceryItemService.getGroceryItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete grocery item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(
            @PathVariable Long id) {

        groceryItemService.deleteGroceryItem(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(
        @PathVariable Long id,
        @Valid @RequestBody GroceryItem groceryItem) {

    return groceryItemService.updateGroceryItem(id, groceryItem)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
