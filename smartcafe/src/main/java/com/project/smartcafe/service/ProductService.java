package com.project.smartcafe.service;

import com.project.smartcafe.domain.product.Category;
import com.project.smartcafe.domain.product.Product;
import com.project.smartcafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Category> findByCategory(Long category_id);

    List<Product> searchByName(String name);

}
