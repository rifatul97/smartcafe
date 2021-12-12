package com.project.smartcafe.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.smartcafe.domain.product.Product;
import com.project.smartcafe.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_cart")
@Data @NoArgsConstructor
public class UserCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Transient
    public String getOrderIdBusiness(){
        DecimalFormat myFormatter = new DecimalFormat("ORD000000");
        return myFormatter.format(id);
    }

    @Column
    private Long user_id;

    @Column
    private String status;

    @Column
    private Timestamp dateCreated;

    /*@JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_cart",
            joinColumns = @JoinColumn(name = "cart_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",
                    referencedColumnName = "id"))
    private List<CartItem> cartProducts;*/

    public UserCart(long userId) {
        this.user_id = userId;
        this.status = OrderStatus.NEW.toString();
        dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return this.id;
    }

}
