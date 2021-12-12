package com.project.smartcafe.service;

import com.project.smartcafe.domain.order.CartItem;
import com.project.smartcafe.domain.order.OrderStatus;
import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.domain.user.User;
import com.project.smartcafe.dto.CartProductDTO;
import com.project.smartcafe.dto.UserCartDto;
import com.project.smartcafe.dto.UserCartProductDto;
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
public class UserCartService {

    @Autowired private UserCartRepository userCartRepo;
    @Autowired private CartItemRepository cartItemRepo;

    public void createUserCart(long userId) {
        userCartRepo.save(new UserCart(userId));
    }

    public List<UserCartDto> getUserCartByStatus(long userId, OrderStatus status) {
        log.info("looking " + userId + " userid's cartitems whose status is = " + status.toString());
        List<UserCartDto> userCartInfo = cartItemRepo
                                            .findAllUserCartProduct(userId, status.toString());
        log.info("it has been found and it is " + userCartInfo);
        return userCartInfo;
    }

    public void addProductToUserCart(long userCartId, long productId, int quantity) {
        cartItemRepo.save(new CartItem(quantity, productId, userCartId));
    }

    public void updateProductToUserCart(long cartItemId, int quantity) {
        cartItemRepo.updateProductQuantity(cartItemId, quantity);
    }


    public String getUserCartStatus(long userCartId) {
        return userCartRepo.getUserCartStatus(userCartId);
    }
}
