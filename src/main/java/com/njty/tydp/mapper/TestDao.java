package com.njty.tydp.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by WCL on 2018/9/3.
 */
public interface TestDao {
    public List<Map<String, Object>> findTestInfo();
}
