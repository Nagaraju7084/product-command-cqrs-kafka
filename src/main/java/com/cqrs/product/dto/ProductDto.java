package com.cqrs.product.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;

}
