package com.cqrs.product.dto;

import com.cqrs.product.entity.Product;
import lombok.Data;

@Data
public class ProductEvent {
    private String eventType;
    private Product product;
}
