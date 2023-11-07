package com.cqrs.product.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "PRODUCT_COMMAND")
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
}
