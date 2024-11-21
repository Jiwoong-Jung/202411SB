package edu.du.sb1120.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataPointRepository extends MongoRepository<DataPoint, String> {
}
