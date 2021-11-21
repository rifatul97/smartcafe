package com.project.smartcafe.repository.user.product;

import com.project.smartcafe.domain.product.Category;
import com.project.smartcafe.domain.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
