package com.cogitoclarus.petstore.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private String customerId;
    private List<JsonNode> items;
}
