package com.cqrs.product.service;

import com.cqrs.product.dto.ProductDto;
import com.cqrs.product.dto.ProductEvent;
import com.cqrs.product.entity.Product;
import com.cqrs.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ProductDto createProduct(ProductDto productDto){
        Product product = productRepository.save(dtoToEntity(productDto));
        ProductEvent event = new ProductEvent();
        event.setProduct(product);
        event.setEventType("CreateProduct");
        kafkaTemplate.send("product-event-topic",event);
        return entityToDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto, Long productId){
       Product product = productRepository.findById(productId).get();
       product.setProductName(productDto.getProductName());
       product.setProductDescription(productDto.getProductDescription());
       product.setProductPrice(productDto.getProductPrice());

       Product updatedProduct = productRepository.save(product);
       ProductEvent event = new ProductEvent();
       event.setProduct(updatedProduct);
       event.setEventType("UpdateProduct");
       kafkaTemplate.send("product-event-topic",event);
       return entityToDto(updatedProduct);
    }

    private Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());
        return product;
    }

    private ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
