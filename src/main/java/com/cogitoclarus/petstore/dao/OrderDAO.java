package com.cogitoclarus.petstore.dao;

import com.cogitoclarus.petstore.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, String> {

    List<Order> findAll();

    List<Order> findByCustomerId(@Param("customer_id") String customerId);

    Order findByOrderId(@Param("order_id") String orderId);

    Order save(Order order);
}