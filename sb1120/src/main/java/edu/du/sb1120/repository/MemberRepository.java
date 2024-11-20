package edu.du.sb1120.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, Integer>  {

    @Query("{id :?0}")  // select * from member where id = ?
    Optional<Member> getMemberBy(Integer id);
}
