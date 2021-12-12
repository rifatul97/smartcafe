package com.project.smartcafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.domain.user.User;
import com.project.smartcafe.dto.*;
import com.project.smartcafe.service.CartService;
import com.project.smartcafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import com.project.smartcafe.dto.RemoveCartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(path = "/api")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart/user") // user can access the 'new'
    @ResponseBody
    public Map<String, String> getUserCartData() throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(auth.getName());

        if (user.isPresent()) {
            Long userId = user.get().getId();
            UserCart userCart = cartService.getUserCart(userId);
            Long userCartId = userCart.getId();
            List<UserCartDto> userCartDtos = cartService.getUserCartByStatus(userId, userCart.getStatus());

            HashMap<String, String> json = new HashMap<>();

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(out, userCartDtos);

            final byte[] data = out.toByteArray();
            System.out.println(new String(data));

            json.put("userId", Long.toString(userId));
            json.put("userCartId", Long.toString((userCartId)));
            json.put("status", userCart.getStatus());
            json.put("dtos", new String(data));

            return json;
        }

        return null;
    }

    @PostMapping("/cart/add")
    public void addProductToCart(@RequestBody AddProductToCartDto dto) {
        cartService.addProductToUserCart((long) dto.getUserCartId(), (long) dto.getProductId(), dto.getQuantity());
    }

    @PostMapping("/cart/update")
    public void updateQuantity(@RequestBody UpdateCartItemDto dto) {
        cartService.updateProductToUserCart(dto.getCartId(), dto.getQuantity());
    }

    @PostMapping("/cart/change") // set cancelled, completed..
    public void updateCartStatus(@RequestBody UpdateCartItemStatusDto dto) {
        cartService.updateCartStatus(dto.getCartId(), dto.getStatus());
    }

    @PostMapping("/cart/remove")
    public void removeCartItem(@RequestBody RemoveCartItemDto dto) {
        cartService.removeCartItem(dto.getCartId());
    }

    @PostMapping("/cart/checkout")
    public void checkOut(@RequestParam("userCartId") long userCartId) {
        cartService.updateCartStatus(userCartId, "PENDING");
    }

    @PostMapping("/cart/cancel")
    public void cancelOrder(@RequestParam("userCartId") long userCartId) {
        cartService.updateCartStatus(userCartId, "CANCEL");
    }


}
