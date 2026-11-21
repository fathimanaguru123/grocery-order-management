
package com.grocery.grocery_order_management.service;

import com.grocery.grocery_order_management.entity.GroceryOrder;
import com.grocery.grocery_order_management.entity.GroceryItem;
import com.grocery.grocery_order_management.repository.GroceryOrderRepository;
import com.grocery.grocery_order_management.repository.CustomerRepository;
import com.grocery.grocery_order_management.repository.GroceryItemRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class GroceryOrderService {

    private final GroceryOrderRepository groceryOrderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository groceryItemRepository;

    public GroceryOrderService(
            GroceryOrderRepository groceryOrderRepository,
            CustomerRepository customerRepository,
            GroceryItemRepository groceryItemRepository) {

        this.groceryOrderRepository = groceryOrderRepository;
        this.customerRepository = customerRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryOrder createOrder(GroceryOrder order) {

        // Find the existing customer
        Long customerId = order.getCustomer().getId();

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        order.setCustomer(customer);

        // Find the existing grocery items
        List<GroceryItem> items = new ArrayList<>();

        for (GroceryItem item : order.getGroceryItems()) {

            GroceryItem existingItem = groceryItemRepository
                    .findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Grocery item not found"));

            items.add(existingItem);
        }

        order.setGroceryItems(items);

        // Calculate total price
        BigDecimal total = BigDecimal.ZERO;

        for (GroceryItem item : items) {
            total = total.add(item.getPrice());
        }

        order.setTotalPrice(total);

        // Set order date automatically
        order.setOrderDate(LocalDateTime.now());

        return groceryOrderRepository.save(order);
    }

    public List<GroceryOrder> getAllOrders() {
        return groceryOrderRepository.findAll();
    }

    public Optional<GroceryOrder> getOrderById(Long id) {
        return groceryOrderRepository.findById(id);
    }

    public void deleteOrder(Long id) {
        groceryOrderRepository.deleteById(id);
    }

    public Optional<GroceryOrder> updateOrder(Long id, GroceryOrder updatedOrder) {

    return groceryOrderRepository.findById(id)
            .map(existingOrder -> {

                // Update customer
                Long customerId = updatedOrder.getCustomer().getId();

                var customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

                existingOrder.setCustomer(customer);

                // Update grocery items
                List<GroceryItem> items = new ArrayList<>();

                for (GroceryItem item : updatedOrder.getGroceryItems()) {

                    GroceryItem existingItem = groceryItemRepository
                            .findById(item.getId())
                            .orElseThrow(() -> new RuntimeException("Grocery item not found"));

                    items.add(existingItem);
                }

                existingOrder.setGroceryItems(items);

                // Calculate total price
                BigDecimal total = BigDecimal.ZERO;

                for (GroceryItem item : items) {
                    total = total.add(item.getPrice());
                }

                existingOrder.setTotalPrice(total);

                return groceryOrderRepository.save(existingOrder);
            });
    }
}

