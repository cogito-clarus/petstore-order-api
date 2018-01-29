package com.cogitoclarus.petstore.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 *  this might be where one would want to store names, billing/shipping addresses, email, phone numbers
 *  payment type/pointers to secure payment type table
 *
 **/
@Data
@Entity
@Table(name = "customer", schema = "sales")
public class Customer extends AbstractEntity {

    @Id
    @Column(name = "customer_id", nullable = false, unique = true, updatable = false)
    private String customerId;

    private Customer() {}

    public Customer(String customerId) {
        super();
        this.customerId = customerId;
    }
}
