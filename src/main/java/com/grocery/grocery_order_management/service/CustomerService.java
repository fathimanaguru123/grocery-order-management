package com.grocery.grocery_order_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grocery.grocery_order_management.entity.Customer;
import com.grocery.grocery_order_management.repository.CustomerRepository;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create or update customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Delete customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {

    return customerRepository.findById(id)
            .map(existingCustomer -> {

                existingCustomer.setName(updatedCustomer.getName());
                existingCustomer.setEmail(updatedCustomer.getEmail());
                existingCustomer.setAddress(updatedCustomer.getAddress());
                existingCustomer.setPhone(updatedCustomer.getPhone());

                return customerRepository.save(existingCustomer);
            });
    }
}
