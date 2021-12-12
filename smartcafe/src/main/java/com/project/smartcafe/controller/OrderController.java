package com.project.smartcafe.controller;

import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.service.CartService;
import com.project.smartcafe.service.UserCartService;
import com.project.smartcafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api")
public class OrderController {

    @Autowired private CartService cartService;
    @Autowired private UserService userService;
    @Autowired private UserCartService userCartService;

    @PreAuthorize("hasRole('ROLE_BARISTA')")
    @GetMapping("/orders")
    public List<UserCart> getOrders() {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_BARISTA')")
    @PostMapping("/order/start")
    public void start(@RequestParam("userCartId") long userCartId) {
        return;
    }

    @PostMapping("/order/check")
    public String orderCheck(@RequestParam("userCartId") long userCartId) {
        return userCartService.getUserCartStatus(userCartId);
    }

    @PreAuthorize("hasRole('ROLE_BARISTA')")
    @PostMapping("/order/fullfill")
    public void fullfill(@RequestParam("userCartId") long userCartId) {
        cartService.updateCartStatus(userCartId, "PENDING");
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/order/cancel")
    public void cancel(@RequestParam("userCartId") long userCartId) {
        return;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/order")
    public UserCart getOrder(@RequestParam("userId") long userId) {
        return null;
    }





}
