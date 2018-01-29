package com.cogitoclarus.petstore.service;

import com.cogitoclarus.petstore.dao.CustomerDAO;
import com.cogitoclarus.petstore.dao.ProductDAO;
import com.cogitoclarus.petstore.model.Customer;
import com.cogitoclarus.petstore.model.Order;
import com.cogitoclarus.petstore.dao.OrderDAO;
import com.cogitoclarus.petstore.model.OrderItem;
import com.cogitoclarus.petstore.dto.OrderDTO;
import com.cogitoclarus.petstore.dto.ProductDTO;
import com.cogitoclarus.petstore.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductApiService productApiService;

    /**
     *
     * @param customerId
     * @return List<Order>
     */
    public List<Order> findByCustomerId(String customerId) {
        return orderDAO.findByCustomerId(customerId);
    }

    /**
     *
     * @param orderId
     * @return Order
     */
    public Order findByOrderId(String orderId) {
        return orderDAO.findByOrderId(orderId);
    }

    /**
     *
     * @param orderDTO
     * @return Order
     */
    public Order save(OrderDTO orderDTO) {

        if (orderDTO != null) {
            Customer customer = new Customer(orderDTO.getCustomerId());
            List<JsonNode> nodes = orderDTO.getItems();
            Set<OrderItem> itemSet = new HashSet<>(orderDTO.getItems().size());
            nodes.stream().forEach(node -> {
                ProductDTO productDTO = productApiService.getProductById(node.get("productId").asText());
                productDAO.save(new Product(productDTO.getId(),productDTO.getCategory(),productDTO.getName(),productDTO.getPrice()));
                itemSet.add(new OrderItem(productDTO.getId(), node.get("quantity").asText(), productDTO.getPrice()));
            });

            customerDAO.save(customer);

            return orderDAO.save(new Order(orderDTO.getCustomerId(),itemSet));
        }

        return null;
    }
}