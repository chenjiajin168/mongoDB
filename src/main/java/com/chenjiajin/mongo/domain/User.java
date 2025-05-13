package com.chenjiajin.mongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document("users") //设置文档所在的集合
public class User implements Serializable {
    //文档的id使用ObjectId类型来封装，并且贴上@Id注解，
    // 自动映射为_id 自动封装ObjectId
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> hobby = new ArrayList<>();
}