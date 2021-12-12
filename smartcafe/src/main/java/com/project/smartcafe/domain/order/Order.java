package com.project.smartcafe.domain.order;

import lombok.Data;
import lombok.Value;
import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.ddd.types.AggregateRoot;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/*
@Data
@Entity
@Table(name = "orders")
public class Order extends AbstractAggregateRoot<Order> implements AggregateRoot<Order, Order.OrderIdentifier> {

    @Id
    private final OrderIdentifier id;
    private final Location location;
    private LocalDateTime orderedDate;
    private OrderStatus status;
    private @Version Long version;

    @OrderColumn //
    @Column(unique = true) //
    private final List<OrderItem> lineItems = new ArrayList();

    public Order(OrderIdentifier id, Location location) {
        this.id = OrderIdentifier.of(UUID.randomUUID().toString());
        this.location = location == null ? Location.TAKE_AWAY : location;
        this.status = OrderStatus.PENDING;
        this.lineItems.addAll(lineItems);
        this.orderedDate = LocalDateTime.now();
    }

    @Value(staticConstructor = "of")
    public static class OrderIdentifier implements Identifier {
        String id;
    }

}

 */