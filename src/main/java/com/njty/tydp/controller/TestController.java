package com.njty.tydp.controller;

import com.njty.tydp.model.TestModel;
import com.njty.tydp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/list")
    public List<TestModel> findAll() {
        return  testService.findTestAll();
    }

    @RequestMapping("/list2")
    public List<Map<String, Object>> findTestInfo() {
        return  testService.findTestInfo();
    }
}
