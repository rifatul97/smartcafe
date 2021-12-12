package com.project.smartcafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToCartDto {
    private int userCartId;
    private int productId;
    private int quantity;
}
