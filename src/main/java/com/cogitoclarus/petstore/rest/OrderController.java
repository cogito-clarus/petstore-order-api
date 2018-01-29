package com.cogitoclarus.petstore.rest;

import com.cogitoclarus.petstore.model.Order;
import com.cogitoclarus.petstore.dto.OrderDTO;
import com.cogitoclarus.petstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

     /**
     *
     * @param customerId
     * @return List<Order>
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{customerId}/order")
    @ResponseBody
    public List<Order> findByCustomerId(@PathVariable String customerId) {
        log.debug("called with: {}", customerId);
        return orderService.findByCustomerId(customerId);
    }

    /**
     *
     * @param orderId
     * @return Order
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/order/{orderId}")
    @ResponseBody
    public Order findByOrderId(@PathVariable String orderId) {
        log.debug("called with: {}", orderId);
        return orderService.findByOrderId(orderId);
    }

    /**
     *
     * @param body
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/order")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Order createOrder(@RequestBody OrderDTO body) {
        log.debug("called with: {}", body);
        return orderService.save(body);
    }
}
