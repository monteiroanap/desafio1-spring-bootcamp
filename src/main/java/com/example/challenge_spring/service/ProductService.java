package com.example.challenge_spring.service;

import com.example.challenge_spring.dto.ProductDto;
import com.example.challenge_spring.model.Product;
import com.example.challenge_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepo repository;

    @Override
    public ProductDto createProduct(Product product) throws IOException {
        int id = repository.getAll().size();
        product.setProductId(id + 1);
        return new ProductDto(repository.saveProduct(product));
    }

    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        List<Product> products = getAll();

        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}