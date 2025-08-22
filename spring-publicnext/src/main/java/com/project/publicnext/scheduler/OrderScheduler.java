package com.project.publicnext.scheduler;

import com.project.publicnext.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderScheduler {

    private final OrderService orderService;

    public OrderScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    // Run every minute
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void runScheduler() {
        orderService.processUnprocessedOrders();
    }
}