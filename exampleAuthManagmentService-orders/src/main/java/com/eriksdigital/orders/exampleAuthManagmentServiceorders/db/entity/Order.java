package com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ORDERS_TABLE")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UID")
    private long id;

    @NonNull
    @Column(name="ORDER_ID")
    private long orderId;

    @NonNull
    @Column(name="ORDER_STATUS")
    private String status;

    @NonNull
    @Column(name="ORDER_PRICE")
    private double totalPrice;

    @NonNull
    @Column(name="ORDER_DATE")
    private String date;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", date='" + date + '\'' +
                '}';
    }
}
