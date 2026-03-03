package com.example.orderservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    // Constructor injection ensures no NullPointerException
    public OrderService(OrderRepository orderRepository,
                        KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Create order
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        // Publish event to Kafka
        OrderEvent event = new OrderEvent(
                savedOrder.getId(),
                savedOrder.getEmail(),
                savedOrder.getProductName(),
                savedOrder.getQuantity()
        );
        kafkaTemplate.send("order-event-topic", event);

        return savedOrder;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}