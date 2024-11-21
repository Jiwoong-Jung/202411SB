package com.example.demo.repository;

import com.example.demo.model.DataPoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataPointRepository extends MongoRepository<DataPoint, String> {
}
