package com.project.potteryshop.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "order-notification";

    public void sendOrderNotification(String message) {
        kafkaTemplate.send(topic, message);
    }
}
