package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Entity.Feedback;
import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Service.FeedbackService;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ApiResponse<Feedback> getImage(
            @RequestParam int voteStar,
            @RequestParam String description,
            @RequestParam String userId,
            @RequestParam String productId) {
        return ApiResponse.<Feedback>builder()
                .code(200)
                .message("Create Feedback Successful!!!")
                .result(feedbackService.createFeedback(voteStar, description, userId, productId))
                .build();
    }

    
}
