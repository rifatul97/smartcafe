package com.project.smartcafe.service;

import com.project.smartcafe.domain.order.CartItem;
import com.project.smartcafe.domain.order.OrderStatus;
import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.domain.user.User;
import com.project.smartcafe.dto.CartProductDTO;
import com.project.smartcafe.dto.UserCartDto;
import com.project.smartcafe.dto.UserCartProductDto;
import com.project.smartcafe.dto.UserCartProductRequestDto;
import com.project.smartcafe.repository.cart.CartItemRepository;
import com.project.smartcafe.repository.cart.UserCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {

    @Autowired private UserCartRepository userCartRepo;
    @Autowired private CartItemRepository cartItemRepo;

    public void createUserCart(long userId) {
        userCartRepo.save(new UserCart(userId));
    }

    public List<UserCartDto> getUserCartByStatus(long userId, String status) {
        List<UserCartDto> userCartInfo = cartItemRepo
                .findAllUserCartProduct(userId, status);
        return userCartInfo;
    }

    public UserCart getUserCart(long userId) {
        List<UserCart> getUserCart = userCartRepo.getUserCartId(userId);
        if (getUserCart == null) {
            this.createUserCart(userId);
        } else {
            for (UserCart userCart: getUserCart) {
                switch (userCart.getStatus()) {
                    case "NEW", "PENDING", "FULLFILLING": {
                        log.info("found usercart which is {}", userCart.toString());
                        return userCart;
                    }
                }
            }
        }
        this.createUserCart(userId);
        return getUserCart(userId);
    }

    public void addProductToUserCart(long userCartId, long productId, int quantity) {
        cartItemRepo.save(new CartItem(quantity, productId, userCartId));
    }

    public void updateProductToUserCart(long cartItemId, int quantity) {
        cartItemRepo.updateProductQuantity(cartItemId, quantity);
    }

    public void updateCartStatus(long cartId, String status) {
        userCartRepo.updateUserCartStatus(cartId, status);
    }

    public void removeCartItem(long cartId) {
        cartItemRepo.removeProductFromCart(cartId);
    }

    public List<UserCartProductDto> getUserCartProduct(UserCartProductRequestDto dto) {
        return userCartRepo.getUserCartProductByStatus(dto.getUsercartid(), dto.getStatus());
    }
}
