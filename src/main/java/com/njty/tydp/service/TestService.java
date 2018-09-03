package com.njty.tydp.service;

import com.njty.tydp.model.TestModel;

import java.util.List;
import java.util.Map;


public interface TestService {

    public List<TestModel> findTestAll();

    public List<Map<String, Object>> findTestInfo();
}
