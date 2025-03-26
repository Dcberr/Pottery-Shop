package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.Discount.DiscountCreateRequest;
import com.project.potteryshop.Entity.Discount;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Mapper.DiscountMapper;
import com.project.potteryshop.Repository.DiscountRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountMapper discountMapper;
    @Autowired
    private ProductRepository productRepository;

    public Discount createDiscount(DiscountCreateRequest request) {
        Discount discount = discountMapper.toDiscount(request);

        List<String> productsId = request.getProductsId();

        List<Product> products = productsId.stream()
                .map(productId -> productRepository.findByProductId(productId))
                .toList();

        // log.info(products.get(0).getProductId());

        discount.setProducts(products);

        // log.info(discount.getProducts().get(0).getProductId());

        discountRepository.save(discount);

        for (Product product : products) {
            product.getDiscounts().add(discount);
            productRepository.save(product); // Lưu từng sản phẩm để cập nhật bảng liên kết
        }

        return discount;
    }

    public Discount getDiscountById(String discountId) {
        return discountRepository.findByDiscountId(discountId);
    }
}
