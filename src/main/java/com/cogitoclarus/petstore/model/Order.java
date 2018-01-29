package com.cogitoclarus.petstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "order", schema = "sales")
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true, updatable = false)
    private String orderId;

    @Column(name = "customer_id", nullable = false, updatable = false)
    private String customerId;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(table = "order_item", name = "item_id")
    private Set<OrderItem> items = new HashSet<OrderItem>();

    private Order() { }

    public Order(String customerId, Set items) {
        super();
        this.customerId = customerId;
        this.items = items;
    }

    public Set<OrderItem> getOrderItems() {
        return Collections.unmodifiableSet(items);
    }

    public void setOrderItem(OrderItem orderItem) {
        items.add(orderItem);
    }

    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            BigDecimal lineTotal = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
            total = total.add(lineTotal);
        }

        return total;
    }
}