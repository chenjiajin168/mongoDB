package com.chenjiajin.mongo.service;

import com.chenjiajin.mongo.domain.User;

import java.util.List;

public interface IUserService {

    void save(User user);

    void delete(String id);

    void update(User user);

    User get(String id);

    List<User> list();

    List<User> findByName(String name);

}