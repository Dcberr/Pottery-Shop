package com.project.potteryshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Entity.Feedback;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Repository.FeedbackRepository;
import com.project.potteryshop.Repository.ProductRepository;
import com.project.potteryshop.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Feedback createFeedback(int voteStar, String description, String userId, String productId) {
        Feedback feedback = new Feedback();
        feedback.setVoteStar(voteStar);
        feedback.setFeedback(description);

        Product product = productRepository.findByProductId(productId);
        feedback.setProduct(product);

        User user = userRepository.findByUserId(userId);
        feedback.setUser(user);

        product.getFeedbacks().add(feedback);
        productRepository.save(product);
        user.getFeedbacks().add(feedback);
        userRepository.save(user);

        feedbackRepository.save(feedback);

        return feedback;
    }

}
