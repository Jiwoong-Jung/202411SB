package project1.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, Integer> {
    //@Query("{id :?0}")
    List<Team> getTeamBy(Integer id);
}
