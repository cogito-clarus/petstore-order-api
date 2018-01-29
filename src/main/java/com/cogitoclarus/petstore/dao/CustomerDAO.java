package com.cogitoclarus.petstore.dao;

import com.cogitoclarus.petstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, String> {

    List<Customer> findAll();

    Customer findByCustomerId(@Param("customer_id") String customerId);

    Customer save(Customer customer);
}
