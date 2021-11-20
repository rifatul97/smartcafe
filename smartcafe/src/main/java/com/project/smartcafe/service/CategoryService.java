package com.project.smartcafe.service;

import com.project.smartcafe.domain.product.Category;
import com.project.smartcafe.repository.product.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Iterable<Category> getAll() {
        return categoryRepo.findAll();
    }
}
