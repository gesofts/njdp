package com.njty.tydp.service.impl;

import com.njty.tydp.mapper.TestDao;
import com.njty.tydp.model.TestModel;
import com.njty.tydp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private TestDao testDao;

    @Override
    public List<TestModel> findTestAll() {
        List<TestModel> list = new ArrayList<>();

        for (int i=0; i<2; i++){
            list.add(new TestModel(i, String.format("jekky_%d", i), String.format("memo_%d", i)));
        }

        return list;
    }

    public List<Map<String, Object>> findTestInfo(){
        return testDao.findTestInfo();
    }

}
