package com.njty.tydp.mapper;

import com.njty.tydp.commom.EntityMapper;
import com.njty.tydp.commom.EntityService;

import java.util.List;
import java.util.Map;

/**
 * Created by WCL on 2018/9/3.
 */
public interface TestMapper extends EntityMapper {
    public List<Map<String, Object>> findTestInfo();
}
