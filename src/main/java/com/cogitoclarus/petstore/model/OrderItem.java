package com.cogitoclarus.petstore.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_item", schema = "sales")
public class OrderItem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false, unique = true, updatable = false)
    private Long itemId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

 //this is here to support what the price was at the time of the order (historical price)
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    private OrderItem() {}

    public OrderItem(String productId, String quantity, String unitPrice) {
        super();
        this.productId = productId;
        this.quantity = new Integer(quantity);
        this.unitPrice = new BigDecimal(unitPrice);
    }
}
