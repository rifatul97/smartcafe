package com.project.smartcafe.service.impl;

import com.project.smartcafe.domain.product.Category;
import com.project.smartcafe.domain.product.Product;
import com.project.smartcafe.repository.product.CategoryRepository;
import com.project.smartcafe.repository.product.ProductRepository;
import com.project.smartcafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepo;
    @Autowired private CategoryRepository categoryRepo;

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Optional<Category> findByCategory(Long category_id) {
        return categoryRepo.findById(category_id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepo.findByNameStartingWith(name);
    }
}
