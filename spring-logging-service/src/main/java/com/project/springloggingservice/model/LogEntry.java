package com.project.springloggingservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntry {
    @Id
    private String id;

    private Long orderId;
    private Double amount;
    private Integer itemsCount;
    private LocalDateTime date;
}