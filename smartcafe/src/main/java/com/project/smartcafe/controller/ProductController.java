package com.project.smartcafe.controller;

import com.project.smartcafe.domain.product.Category;
import com.project.smartcafe.domain.product.Product;
import com.project.smartcafe.domain.user.User;
import com.project.smartcafe.service.CategoryService;
import com.project.smartcafe.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Slf4j
@RequestMapping(path = "/api")
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/product/category/")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam(value = "id") long id) {
        if (id == 0) {
            return null;
        }
        return ResponseEntity.ok(productService.findByCategory(id).get().getProducts());
    }

    @GetMapping("/product/search/")
    public ResponseEntity<List<Product>> getProductsByStartingName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    @GetMapping("/product/categories")
    public ResponseEntity<Iterable<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }




}
