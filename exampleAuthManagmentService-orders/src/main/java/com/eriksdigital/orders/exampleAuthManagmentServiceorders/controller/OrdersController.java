package com.eriksdigital.orders.exampleAuthManagmentServiceorders.controller;


import com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.dto.OrderDto;
import com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.entity.Order;
import com.eriksdigital.orders.exampleAuthManagmentServiceorders.db.repository.OrderRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * check connection with jwt token to port 7075
     *
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(value = "/hello",produces = "application/json")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("hello secure port 7075 ", HttpStatus.OK);
    }

    /**
     * add new Order
     *
     *
     * @param orderJson
     * @return ResponseEntity
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping(value = "/add-new-order",produces = "application/json")
    public ResponseEntity<?> addNewOrder(@RequestBody String orderJson){

        JSONObject jsonObject = new JSONObject(orderJson);
        String id = jsonObject.getString("orderId");
        double price = jsonObject.getDouble("totalPrice");
        String status = jsonObject.getString("status");
        String date = jsonObject.getString("date");

        if(orderRepository.isOrderExists(id)>0) return new ResponseEntity<>("Order with this Id already exists",HttpStatus.BAD_REQUEST);

        OrderDto orderDto = new OrderDto(id,status,price,date);
        Order tempOrder = modelMapper.map(orderDto,Order.class);
        orderRepository.save(tempOrder);
        return new ResponseEntity<>("order was added "+tempOrder.toString(),HttpStatus.OK);
    }

    /**
     * This method deals with deleting of exists order , note that you must send the param as a path extention
     * and not as a requestparam so not like /.../something?value=12345
     * @param orderId
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{orderId}")
    public ResponseEntity<?> removeOrder(@PathVariable String orderId ){


        if(orderRepository.isOrderExists(orderId)==0){
            return new ResponseEntity<>("Order was not found",HttpStatus.NOT_FOUND);
        }
        Order temp = orderRepository.getOrderByOrderId(orderId);
        orderRepository.delete(temp);
        return new ResponseEntity<>("order removed successfully : "+ orderId,HttpStatus.OK);
    }

    /**
     * Get order by orderId
     * @param orderId
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(value = "/get-order/{orderId}",produces = "application/json")
    public ResponseEntity<?> getOrder(@PathVariable String orderId ){


        if(orderRepository.isOrderExists(orderId)==0){
            return new ResponseEntity<>("Order was not found",HttpStatus.NOT_FOUND);
        }
        Order temp = orderRepository.getOrderByOrderId(orderId);
        return new ResponseEntity<>(temp.toString(),HttpStatus.OK);
    }


    /**
     * Get all orders
     *
     * @return a ResponseEntity with all the orders in the system
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(value = "/get-all-order",produces = "application/json")
    public ResponseEntity<?> getAllOrder(){

        List<Order> tempList = orderRepository.getAllOrder();
        return new ResponseEntity<>(tempList,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping(value = "/update",produces = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody String updateJson){
        JSONObject jsonObject = new JSONObject(updateJson);
        String id = jsonObject.getString("orderId");
        double price = jsonObject.getDouble("totalPrice");
        String status = jsonObject.getString("status");
        String date = jsonObject.getString("date");
        if(orderRepository.isOrderExists(id) == 0 ){
            return new ResponseEntity<>("Order was not found orderId:  "+ id,HttpStatus.NOT_FOUND);
        }
        Order temp = orderRepository.getOrderByOrderId(id);
        temp.setStatus(status);
        temp.setTotalPrice(price);
        temp.setDate(date);

        orderRepository.save(temp);

        return new ResponseEntity<>("Order updated "+temp,HttpStatus.OK);
    }

}
