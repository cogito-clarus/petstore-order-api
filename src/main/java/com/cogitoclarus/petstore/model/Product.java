package com.cogitoclarus.petstore.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/***
 * This entity isn't strictly needed except for perhaps reporting without hitting product API
 * a composite primary key or adding a create timestamp query (see AbstractEntity.java) could make a handy historical table
 */
@Data
@Entity
@Table(name = "product", schema = "sales")
public class Product extends AbstractEntity {

    @Id
    @Column(name = "product_id", nullable = false, unique = true, updatable = false)
    private String productId; //0a207870

    @Column(name = "category", nullable = false)
    private String category; //fish

    @Column(name = "product_name", nullable = false)
    private String productName;  //Sparkly Castle

    @Column(name = "price", nullable = false)
    private BigDecimal price; //9.95

    private Product() {}

    public Product(String productId, String category, String productName, String price) {
        super();
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.price = new BigDecimal(price);
    }
}
