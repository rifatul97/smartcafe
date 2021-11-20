package com.project.smartcafe.repository.user.product;

import com.project.smartcafe.domain.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameStartingWith(String name);

}
