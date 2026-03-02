package com.abhay.moviesManagement.repository;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
