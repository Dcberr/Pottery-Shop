package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.OrderCreateRequest;
import com.project.potteryshop.Dto.Response.OrderCreateResponse;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Entity.UserOrder;
import com.project.potteryshop.Repository.OrderRepository;
import com.project.potteryshop.Repository.ProductRepository;
import com.project.potteryshop.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public OrderCreateResponse createOrder(OrderCreateRequest request) {
        UserOrder order = new UserOrder();
        order.setPaymentMethod(request.getPaymentMethod());

        User user = userRepository.findByUserId(request.getUserId());
        order.setUser(user);

        List<String> productsId = request.getProductsId();
        List<Product> products = productsId.stream()
                .map(productId -> productRepository.findByProductId(productId))
                .toList();
        order.setProducts(products);
        order.setNumOfProduct(products.size());

        float totalPrice = products.stream()
                .map(product -> product.getProductItem().getPrice())
                .reduce(0f, Float::sum);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        for (Product product : products) {
            product.getOrders().add(order);
            productRepository.save(product); // Lưu từng sản phẩm để cập nhật bảng liên kết
        }

        return new OrderCreateResponse(order.getOrderId(), user.getName(), user.getPhoneNumber(), user.getAddress(),
                products.size(),
                totalPrice, request.getPaymentMethod(), products);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserOrder> getAllUserOrder() {
        return orderRepository.findAll().stream().toList();
    }

    @PreAuthorize("hasAuthority('GET_ORDER')")
    public UserOrder getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
