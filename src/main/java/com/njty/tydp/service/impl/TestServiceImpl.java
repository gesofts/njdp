package com.njty.tydp.service.impl;

import com.njty.tydp.model.TestModel;
import com.njty.tydp.service.TestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public List<TestModel> findTestAll() {
        List<TestModel> list = new ArrayList<>();

        for (int i=0; i<2; i++){
            list.add(new TestModel(i, String.format("jekky_%d", i), String.format("memo_%d", i)));
        }

        return list;
    }
}
