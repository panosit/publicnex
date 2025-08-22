package com.project.publicnext.serviceImpl;

import com.project.publicnext.model.Order;
import com.project.publicnext.model.OrderLine;
import com.project.publicnext.repository.OrderRepository;
import com.project.publicnext.service.LoggingClient;
import com.project.publicnext.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        // ensure each OrderLine points back to parent Order
        if (order.getOrderLines() != null) {
            for (OrderLine line : order.getOrderLines()) {
                line.setOrder(order);
            }
        }
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id)
            .map(existingOrder -> {
                existingOrder.setCustomerName(order.getCustomerName());
                existingOrder.setStatus(order.getStatus());

                if (order.getOrderLines() != null) {
                    for (OrderLine line : order.getOrderLines()) {
                        line.setOrder(existingOrder); // <-- important
                    }
                    existingOrder.setOrderLines(order.getOrderLines());
                }

                return orderRepository.save(existingOrder);
            }).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Autowired
    private LoggingClient loggingClient; // inject the client

    @Override
    public void processUnprocessedOrders() {
    List<Order> unprocessedOrders = orderRepository.findByStatus("unprocessed");
    for (Order order : unprocessedOrders) {
        // 1️⃣ Update order status
        order.setStatus("processed");
        orderRepository.save(order);

        // 2️⃣ Send log to logging service
        try {
            loggingClient.sendLog(order);
            System.out.println("Log sent for order: " + order.getOrderId());
        } catch (Exception e) {
            System.err.println("Failed to send log for order " + order.getOrderId() + ": " + e.getMessage());
        }

        // 3️⃣ Print confirmation
        System.out.println("Processed order: " + order);
    }
}

}
