package com.project.smartcafe.repository.cart;

import com.project.smartcafe.domain.order.UserCart;
import com.project.smartcafe.dto.UserCartProductDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository extends CrudRepository<UserCart, Long> {

    @Modifying
    @Query(value = "Update user_cart SET status = :status WHERE id = :cartId", nativeQuery = true)
    void updateUserCartStatus(long cartId, String status);

    @Query(value = "Select products.name as name, c.quantity as quantity from cart_item c\n" +
            "join user_cart on c.cart_id = user_cart.id\n" +
            "join products on c.product_id = products.id\n" +
            "where user_cart.id = :user_cart_id AND user_cart.status = :status", nativeQuery = true)
    List<UserCartProductDto> getUserCartProductByStatus(long user_cart_id, String status);

    @Query(value = "Select * from user_cart where user_id = :user_id", nativeQuery = true)
    List<UserCart> getUserCartId(long user_id);

    @Query(value = "Select status from user_cart where id = :userCartId", nativeQuery = true)
    String getUserCartStatus(long userCartId);
}