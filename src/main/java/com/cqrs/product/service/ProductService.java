package com.cqrs.product.service;

import com.cqrs.product.dto.ProductDto;
import com.cqrs.product.entity.Product;
import com.cqrs.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto){
        return entityToDto(productRepository.save(dtoToEntity(productDto)));
    }

    public ProductDto updateProduct(ProductDto productDto, Long productId){
       Product product = productRepository.findById(productId).get();
       product.setProductName(productDto.getProductName());
       product.setProductDescription(productDto.getProductDescription());
       product.setProductPrice(productDto.getProductPrice());

       Product updatedProduct = productRepository.save(product);
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
