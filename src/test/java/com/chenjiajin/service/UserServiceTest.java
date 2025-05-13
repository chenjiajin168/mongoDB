package com.chenjiajin.service;

import com.chenjiajin.mongo.domain.User;
import com.chenjiajin.mongo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * JPA基本操作
 *
 * @author com.chenjiajin
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private IUserService userService;  // 底层就是MongoTemplate , 满足JPA规范


    @Test
    public void testSave() {
        User user = new User();
        user.setName("dafei");
        user.setAge(18);
        userService.save(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("5de507fca0852c6c7ebc1eac");
        user.setName("dafei2222");
        user.setAge(18);
        userService.update(user);
    }

    @Test
    public void testDelete() {
        userService.delete("5de507fca0852c6c7ebc1eac");
    }

    @Test    //根据id查询
    public void testGet() {
        System.out.println(userService.get("5de507fca0852c6c7ebc1eac"));
    }

    @Test   //全部查询
    public void testList() {
        System.out.println(userService.list());
    }




    @Test   // 测试JPA规范
    public void jpa_demo1() throws Exception {
        List<User> dafei = userService.findByName("dafei");
        System.err.println("dafei = " + dafei);
    }

    @Test   // 测试JPA规范
    public void save() throws Exception {
        User user = new User();
        user.setAge(1);
        user.setName("aa");
        user.setHobby(Arrays.asList("1", "2"));
        userService.save(user);
    }


}