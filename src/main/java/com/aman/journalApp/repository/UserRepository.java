package com.aman.journalApp.repository;

import com.aman.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    // greating a method
    User findByUserName(String userName);

    User deleteByUserName(String userName);

}
