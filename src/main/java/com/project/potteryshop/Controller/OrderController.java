package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.OrderCreateRequest;
import com.project.potteryshop.Dto.Response.OrderCreateResponse;
import com.project.potteryshop.Entity.UserOrder;
import com.project.potteryshop.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    private ApiResponse<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
        return ApiResponse.<OrderCreateResponse>builder()
                .code(200)
                .message("Create Order Successfull!!")
                .result(orderService.createOrder(request))
                .build();
    }

    @GetMapping
    private ApiResponse<List<UserOrder>> getAllOrders() {
        return ApiResponse.<List<UserOrder>>builder()
                .code(200)
                .message("Create Order Successfull!!")
                .result(orderService.getAllUserOrder())
                .build();
    }

    @GetMapping("/{orderId}")
    private ApiResponse<UserOrder> getOrderById(@PathVariable String orderId) {
        return ApiResponse.<UserOrder>builder()
                .code(200)
                .message("Create Order Successfull!!")
                .result(orderService.getOrderById(orderId))
                .build();
    }
}
