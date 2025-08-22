package com.project.springloggingservice.controller;

import com.project.springloggingservice.model.LogEntry;
import com.project.springloggingservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @PostMapping    
    public ResponseEntity<LogEntry> createLog(@RequestBody LogEntry logEntry) {
        return ResponseEntity.ok(logRepository.save(logEntry));
    }
}