package com.project.smartcafe.repository.user.cart;

import com.project.smartcafe.domain.order.CartItem;
import com.project.smartcafe.dto.UserCartDto;
import com.project.smartcafe.dto.UserCartProductDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    @Query(value = "Select c.id as cartItemId, c.product_id as productId, c.quantity as quantity from cart_item c\n" +
            "join user_cart on c.cart_id = user_cart.id\n" +
            "join products on c.product_id = products.id\n" +
            "where user_cart.user_id = :user_id and status = :status", nativeQuery = true)
    List<UserCartDto> findAllUserCartProduct(long user_id, String status);

    @Query(value = "Delete From cart_item where cart_id = :cartId", nativeQuery = true)
    void removeProductFromCart(long cartId); //just use delete.

    @Modifying
    @Query(value = "Update cart_item SET quantity = :quantity where id = :cartItemId", nativeQuery = true)
    void updateProductQuantity(long cartItemId, int quantity);

    // not needed: this is to preserve user's history
    //void deleteAllByUserCartId(long userCartId);




}
