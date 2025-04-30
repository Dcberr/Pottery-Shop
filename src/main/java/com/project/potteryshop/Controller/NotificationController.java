package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Service.NotificationProducer;
import com.project.potteryshop.Service.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationProducer notificationProducer;

    @PostMapping
    public ApiResponse<Void> sendMessage(@RequestBody String message) {
        notificationProducer.sendOrderNotification(message);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Send Notification: " + message + " Successful!!!")
                .build();
    }

    @GetMapping
    public ApiResponse<List<String>> getNotificationHistory() {
        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Get Notification History Successful!!!")
                .result(notificationService.getNotificationHistory())
                .build();
    }

}
