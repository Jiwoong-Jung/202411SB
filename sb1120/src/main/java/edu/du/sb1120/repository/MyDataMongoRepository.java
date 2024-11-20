package edu.du.sb1120.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, String> {
    public List<MyDataMongo> findByName(String s);
    public List<MyDataMongo> findByNameLike(String s);

    List<MyDataMongo> getMyDataMongoByMemoLike(String s);

    @Query("{name :  ?0}")   // select * from mydatamongo where name = ?
    List<MyDataMongo> listByName(String name);
}
