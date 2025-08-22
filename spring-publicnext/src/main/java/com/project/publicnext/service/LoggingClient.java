package com.project.publicnext.service;

import com.project.publicnext.dto.LogDto;
import com.project.publicnext.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Service
public class LoggingClient {

    private final RestTemplate restTemplate;

    public LoggingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendLog(Order order) {
        String url = "http://localhost:8081/logs"; // Logging Service endpoint

        LogDto log = new LogDto();
        log.setOrderId(order.getOrderId());
        log.setAmount(order.getOrderLines().stream().mapToDouble(ol -> ol.getPrice() * ol.getQuantity()).sum());
        log.setItemsCount(order.getOrderLines().size());
        log.setDate(LocalDateTime.now());

        restTemplate.postForEntity(url, log, String.class);
    }
}