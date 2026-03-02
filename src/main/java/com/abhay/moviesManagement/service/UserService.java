package com.abhay.moviesManagement.service;

import com.abhay.moviesManagement.entity.User;
import com.abhay.moviesManagement.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
  return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId ID) {
        return userRepository.findById(ID);
    }


    public void deleteAll() {
        userRepository.deleteAll();
    }


    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        User username = userRepository.findByUserName(userName);
        return username;
    }


}

