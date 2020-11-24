package com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Component
public class OrderDto {

    @NonNull
    private String orderId;

    @NonNull
    private String status;

    @NonNull
    private double totalPrice;

    @NonNull
    private String date;

}
