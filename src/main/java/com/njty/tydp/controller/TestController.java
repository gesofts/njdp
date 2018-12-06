package com.njty.tydp.controller;

import com.njty.tydp.commom.EntityController;
import com.njty.tydp.commom.EntityService;
import com.njty.tydp.model.MsgModel;
import com.njty.tydp.model.TestModel;
import com.njty.tydp.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController extends EntityController {

    protected static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping("/list")
    public List<TestModel> findAll() {
        return  testService.findTestAll();
    }

    @RequestMapping("/list2")
    public List<Map<String, Object>> findTestInfo() {
        logger.info("TestController - findTestInfo");
        return  testService.findTestInfo();
    }


    @Override
    public EntityService getEntityService() {
        return this.testService;
    }


}
