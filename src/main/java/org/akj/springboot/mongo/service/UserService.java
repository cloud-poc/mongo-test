package org.akj.springboot.mongo.service;

import org.akj.springboot.mongo.bean.User;
import org.akj.springboot.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public Page<User> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "lastUpdateDate");
        Page<User> users = userRepository.findAll(pageable);

        return users;
    }
}
