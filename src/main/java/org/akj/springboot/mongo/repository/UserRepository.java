package org.akj.springboot.mongo.repository;

import org.akj.springboot.mongo.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
}
