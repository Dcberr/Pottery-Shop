package com.project.potteryshop.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.OrderCreateRequest;
import com.project.potteryshop.Dto.Response.OrderCreateResponse;
import com.project.potteryshop.Entity.Discount;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Entity.UserOrder;
import com.project.potteryshop.Repository.DiscountRepository;
import com.project.potteryshop.Repository.OrderRepository;
import com.project.potteryshop.Repository.ProductRepository;
import com.project.potteryshop.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final DiscountService discountService;

    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public OrderCreateResponse createOrder(OrderCreateRequest request) {
        UserOrder order = new UserOrder();
        order.setPaymentMethod(request.getPaymentMethod());

        // User user = userRepository.findByUserId(request.getUserId());
        SecurityContext context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow();
        order.setUser(user);

        // Lấy danh sách sản phẩm từ danh sách id
        List<Product> products = request.getProductsId().stream()
                .map(productRepository::findByProductId)
                .filter(Objects::nonNull)
                .toList();

        order.setProducts(products);
        order.setNumOfProduct(products.size());

        // Lấy map giữa productId và discountId do người dùng gửi lên
        Map<String, String> discountMap = request.getDiscountMap();
        float totalPrice = 0f;

        // Tính tổng giá sau giảm giá cho từng sản phẩm
        for (Product product : products) {
            float originalPrice = product.getProductItem().getPrice();
            float finalPrice = originalPrice;

            String productId = product.getProductId();
            String discountId = discountMap.get(productId);

            Boolean vadlidDiscount = discountService.getValidDiscountsByProductId(productId)
                    .contains(discountRepository.findByDiscountId(discountId));

            if (discountId != null) {
                Discount discount = discountRepository.findByDiscountId(discountId);

                // Kiểm tra giảm giá hợp lệ
                if (vadlidDiscount) {

                    float discountPercent = discount.getPercentDiscount();
                    finalPrice = originalPrice * (1 - discountPercent / 100f);
                } else {
                    throw new RuntimeException("Invalid Discount!!!");
                }
            }

            totalPrice += finalPrice;
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        // Cập nhật mối liên kết giữa product và order
        for (Product product : products) {
            product.getOrders().add(order);
            productRepository.save(product);
        }

        return new OrderCreateResponse(
                order.getOrderId(),
                user.getName(),
                user.getPhoneNumber(),
                user.getAddress(),
                products.size(),
                totalPrice,
                request.getPaymentMethod(),
                products);
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
