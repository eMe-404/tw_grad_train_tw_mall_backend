package com.tw.mall.service;

import com.tw.mall.entity.Product;
import com.tw.mall.exeption.ProductNotFoundException;
import com.tw.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product product) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setId(id);
        return productRepository.save(product);
    }
}
