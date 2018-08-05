package com.tw.mall.controller;

import com.tw.mall.entity.Product;
import com.tw.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProduct = productService.getAll();
        return ResponseEntity.ok(allProduct);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.noContent().build();
    }
}
