package com.project.smartcafe.domain.order;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.smartcafe.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
@Data @NoArgsConstructor @AllArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "cart_id")
    private Long userCartId;

    @Column
    private int quantity;

    public CartItem(long id, int quantity, Long productId, Long userCartId) {
        this.id = id;
        this.userCartId = userCartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(int quantity, Long productId, Long userCartId) {
        this.userCartId = userCartId;
        this.productId = productId;
        this.quantity = quantity;
    }

}
