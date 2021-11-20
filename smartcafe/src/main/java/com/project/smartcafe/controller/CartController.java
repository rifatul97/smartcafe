package com.project.smartcafe.controller;

import com.project.smartcafe.domain.order.OrderStatus;
import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.dto.UserCartDto;
import com.project.smartcafe.dto.UserCartProductDto;
import com.project.smartcafe.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/user/{userId}/list") // user can access the 'new'
    public HashMap<Long, List<UserCartDto>> getUserCartData(@PathVariable("userId") long userId) {
        UserCart userCart = cartService.getUserCart(userId);
        Long userCartId = userCart.getId();
        List<UserCartDto> userCartDtos = cartService.getUserCartByStatus(userId, OrderStatus.NEW);

        HashMap<Long, List<UserCartDto>> userStat = new HashMap<>();
        userStat.put(userCartId, userCartDtos);
        return userStat;
    }

    @PostMapping("/cart/{userCartId}/product/{productId}/quantity/{quantity}")
    public void addProductToCart(@PathVariable("userCartId") long userCartId,
                                 @PathVariable("productId") long productId,
                                 @PathVariable("quantity") int quantity) {
        cartService.addProductToUserCart(userCartId, productId, quantity);
    }

    @PostMapping("/cart/{cartId}/quantity/{quantity}")
    public void updateQuantity(@PathVariable("cartId") long cartId,
                               @PathVariable("quantity") int quantity) {
        cartService.updateProductToUserCart(cartId, quantity);
    }


    @PostMapping("/cart/{cartId}/status/{status}") // set cancelled, completed..
    public void updateCartStatus(long cartId, String status) {
        cartService.updateCartStatus(cartId, status);
    }

    @DeleteMapping("/cart/{cartId}/remove")
    public void removeCartItem(long cartId) {
        cartService.removeCartItem(cartId);
    }

    @GetMapping("/cart/user/{}/checkout")
    public List<UserCartProductDto> checkOut() {
        return null;
    }




}
