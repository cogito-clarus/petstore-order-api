package com.cogitoclarus.petstore.dao;

import com.cogitoclarus.petstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {

    List<Product> findAll();

    Product findByProductId(@Param("product_id") String productId);

    Product save(Product product);
}
