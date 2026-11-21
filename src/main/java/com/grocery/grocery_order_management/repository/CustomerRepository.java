package com.grocery.grocery_order_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.grocery_order_management.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
