package com.chenjiajin.mongo.service.impl;

import com.chenjiajin.mongo.domain.User;
import com.chenjiajin.mongo.repository.UserMongoRepository;
import com.chenjiajin.mongo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public void save(User user) {
        userMongoRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userMongoRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        userMongoRepository.save(user);
    }

    @Override
    public User get(String id) {

        // get()方法可以获取到值，但是直接这样写的话如果值不存在就要空指针。所以要先做判断，值存在再get()，或者就是写在try-catch里。
        //return productRepository.findById(id).get();

        // 存在就会直接返回值，如果不存在会返回别的值，这里不存在返回的是null
        return userMongoRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> list() {
        return userMongoRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userMongoRepository.findByName(name);
    }
}