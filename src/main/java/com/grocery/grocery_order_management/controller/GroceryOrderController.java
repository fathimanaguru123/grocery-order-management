
package com.grocery.grocery_order_management.controller;

import com.grocery.grocery_order_management.entity.GroceryOrder;
import com.grocery.grocery_order_management.service.GroceryOrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class GroceryOrderController {

    private final GroceryOrderService groceryOrderService;

    public GroceryOrderController(GroceryOrderService groceryOrderService) {
        this.groceryOrderService = groceryOrderService;
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<GroceryOrder> createOrder(
            @RequestBody GroceryOrder order) {

        GroceryOrder savedOrder =
                groceryOrderService.createOrder(order);

        return ResponseEntity.ok(savedOrder);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<GroceryOrder>> getAllOrders() {
        return ResponseEntity.ok(
                groceryOrderService.getAllOrders());
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroceryOrder> getOrderById(
            @PathVariable Long id) {

        return groceryOrderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id) {

        groceryOrderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryOrder> updateOrder(
        @PathVariable Long id,
        @RequestBody GroceryOrder order) {

    return groceryOrderService.updateOrder(id, order)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}

