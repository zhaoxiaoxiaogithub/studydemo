package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JdbcConnectionService {
    @Autowired
    JdbcTemplate jdbcTemplate;
//脏读
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void test_read_uncommitted() {
        jdbcTemplate.execute("update into test set name='113' where password='123' ");
        List<User> list = jdbcTemplate.queryForList("select name from test where password='123'", User.class);
        System.out.println(list);
    }
//
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void test_read_commited() {
        List<User> list = jdbcTemplate.queryForList("select *from test", User.class);
        System.out.println(list);

    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void test_repeatable_read() {
        List<User> list = jdbcTemplate.queryForList("select *from test", User.class);
        System.out.println(list);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void test_serializable() {
        List<User> list = jdbcTemplate.queryForList("select *from test", User.class);
        System.out.println(list);
    }
}
