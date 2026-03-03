package com.example.inventoryservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "order-event-topic", groupId = "inventory-group")
    public void consume(OrderEvent event) {

        System.out.println("📦 INVENTORY SERVICE");
        System.out.println("Reducing stock for: " + event.getProductName());
        System.out.println("Quantity: " + event.getQuantity());
        System.out.println("---------------------------");
    }
}