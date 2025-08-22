package com.project.springloggingservice.repository;

import com.project.springloggingservice.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogEntry, String> {}