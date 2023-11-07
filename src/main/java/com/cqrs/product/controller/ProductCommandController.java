package com.cqrs.product.controller;

import com.cqrs.product.dto.ProductDto;
import com.cqrs.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductCommandController {

    @Autowired
    private ProductService productService;
    @PostMapping("/product/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @PutMapping("/product/updateProduct/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable Long productId){
        return new ResponseEntity<>(productService.updateProduct(productDto, productId), HttpStatus.ACCEPTED);
    }

}
