package com.chenjiajin.service;

import com.chenjiajin.mongo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
public class QueryTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    // 分页查询文档，显示第2页，每页显示3个，按照id升序排列
    @Test  //分页方式1
    public void testQuery1() throws Exception {
        // 创建查询对象:  拼接MQL的类
        Query query = new Query();
        // 设置分页信息 跳过3个,限制3个
        query.skip(3).limit(3);

        // 设置排序规则: ASC正序 , 根据_id列来排
        query.with(Sort.by(Sort.Direction.ASC, "_id"));

        List<User> list = mongoTemplate.find(query, User.class, "users");
        //因为User类上面已经指定列表的名称 , 所以可以不指定表名 , 建议明确指定
        list.forEach(System.err::println);
    }

    // 分页查询文档，显示第2页，每页显示3个，按照id升序排列
    @Test  //分页方式2
    public void testQuery22() throws Exception {

        Query query = new Query();
        // 参数: 当前页(此处是以0开始表示第一页)  每页显示条数  排序规则 排序对象
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.ASC, "_id");
        query.with(pageRequest);

        List<User> list = mongoTemplate.find(query, User.class, "users");
        //因为User类上面已经指定列表的名称 , 所以可以不指定表名 , 建议明确指定
        list.forEach(System.err::println);
    }

    // 查询所有name为dafei的文档
    @Test
    public void testQuery2() throws Exception {
        // 构建限制条件 {"name": "dafei"}
        Criteria criteria = Criteria.where("name").is("dafei");
        // 创建查询对象
        Query query = new Query();
        // 添加限制条件
        query.addCriteria(criteria);

        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    // 查询所有name为dafei或者age<30的文档
    @Test
    public void testQuery3() throws Exception {
        // 构建限制条件 { "$or": [{"name": "dafei"}, {"age": {"$lt": 30}}] }
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("name").is("dafei"),
                Criteria.where("age").lt(30)
        );
        // 创建查询对象
        Query query = new Query();
        // 添加限制条件
        query.addCriteria(criteria);

        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    // 查询所有name含有fei并且30<=age<=32的文档
    @Test
    public void testQuery4() throws Exception {
        // 构建限制条件 { "$and" : [{"name": {"$regex": ".*fei.*"} }, {"age": {"$gte": 30, "$lte": 32 } }] }
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("name").regex(".*fei.*"),
                Criteria.where("age").gte(30).lte(32)
        );
        // 创建查询对象
        Query query = new Query();
        // 添加限制条件
        query.addCriteria(criteria);

        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }


}
