package com.project.smartcafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CartProductDTO {
    private Long user_cart_id;
    private Long product_id;
    private int quantity;
}
