package com.chenjiajin.mongo.repository;

import com.chenjiajin.mongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    // 使用Spring Data命名规范做高级查询
    List<User> findByName(String name);
}