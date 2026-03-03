package com.example.emailservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "order-event-topic", groupId = "email-group")
    public void consume(OrderEvent event) {

        System.out.println("📧 EMAIL SERVICE");
        System.out.println("Sending email to: " + event.getEmail());
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("---------------------------");
    }
}