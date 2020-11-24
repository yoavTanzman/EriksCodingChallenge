package com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.repository;

import com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query(value = "SELECT COUNT(ORDER_ID) FROM ORDERS_TABLE u " +
            "WHERE ORDER_ID =:orderId",nativeQuery = true)
    int isOrderExists(String orderId);

    @Query(value = "SELECT * FROM ORDERS_TABLE u " +
            "WHERE ORDER_ID =:orderId",nativeQuery = true)
    Order getOrderByOrderId(String orderId);

    @Query(value = "SELECT * FROM ORDERS_TABLE u ",nativeQuery = true)
    List<Order> getAllOrder();

}
