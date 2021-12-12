package com.project.smartcafe.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private double price;

    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "product_category",
                joinColumns = @JoinColumn(name = "product_id",
                    referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "id"))
    private Category category;

}
