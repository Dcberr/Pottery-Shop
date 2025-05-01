package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final StringRedisTemplate stringRedisTemplate;

    private final String notificationHistoryKey = "notification-history";

    public List<String> getNotificationHistory() {
        List<String> notifications = stringRedisTemplate.opsForList().range(notificationHistoryKey, 0, -1);
        return notifications;
    }
}
