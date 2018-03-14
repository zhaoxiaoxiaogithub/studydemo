package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JdbcConnectionController {

    @Autowired
    private JdbcConnectionService service;

    @RequestMapping("test_read_uncommitted")
    public void test(){
        service.test_read_uncommitted();
    }
}
