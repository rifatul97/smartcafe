package com.project.smartcafe.controller;

import com.project.smartcafe.domain.order.OrderStatus;
import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.dto.UserCartDto;
import com.project.smartcafe.dto.UserCartProductDto;
import com.project.smartcafe.service.UserCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(path = "/api")
public class CartController {

    @Autowired
    private UserCartService userCartService;

    @GetMapping("/cart/user/{userId}/list") // user can access the 'new'
    public HashMap<Long, List<UserCartDto>> getUserCartData(@PathVariable("userId") long userId) {
        UserCart userCart = userCartService.getUserCart(userId);
        Long userCartId = userCart.getId();
        List<UserCartDto> userCartDtos = userCartService.getUserCartByStatus(userId, OrderStatus.NEW);

        HashMap<Long, List<UserCartDto>> userStat = new HashMap<>();
        userStat.put(userCartId, userCartDtos);
        return userStat;
    }

    @PostMapping("/cart/{userCartId}/product/{productId}/quantity/{quantity}")
    public void addProductToCart(@PathVariable("userCartId") long userCartId,
                                 @PathVariable("productId") long productId,
                                 @PathVariable("quantity") int quantity) {
        userCartService.addProductToUserCart(userCartId, productId, quantity);
    }

    @PostMapping("/cart/{cartId}/quantity/{quantity}")
    public void updateQuantity(@PathVariable("cartId") long cartId,
                               @PathVariable("quantity") int quantity) {
        userCartService.updateProductToUserCart(cartId, quantity);
    }


    @PostMapping("/cart/user/{userId}/status/{status}") // set cancelled, completed..
    public void updateCartStatus(long userId, String status) {

    }

    @GetMapping("/cart/user/{}/checkout")
    public List<UserCartProductDto> checkOut() {
        return null;
    }




}
