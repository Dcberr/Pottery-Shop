package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.Discount.DiscountCreateRequest;
import com.project.potteryshop.Entity.Discount;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Mapper.DiscountMapper;
import com.project.potteryshop.Repository.DiscountRepository;
import com.project.potteryshop.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final ProductRepository productRepository;

    @PreAuthorize("hasAuthority('CREATE_DISCOUNT')")
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

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll().stream().toList();
    }

    public List<Discount> getValidDiscountsByProductId(String productId) {
        return discountRepository.findAllDiscountByProductId(productId);
    }
}
