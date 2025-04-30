package com.project.potteryshop.Service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final StringRedisTemplate stringRedisTemplate;
    private final String notificationHistoryKey = "notification-history";

    @KafkaListener(topics = "order-notification", groupId = "notification-group")
    public void listen(String message) {
        System.out.println("🔔 Real-time Notification Received: " + message);
        stringRedisTemplate.opsForList().leftPush(notificationHistoryKey, message);

    }
}
