/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EntityMapper
 * Author:   wangcl
 * Date:     2018/12/6 下午2:11
 * Description: 基础
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.njty.tydp.commom;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基础〉
 *
 * @author wangcl
 * @create 2018/12/6
 * @since 1.0.0
 */
public interface EntityMapper {


    public int add(Map<String, Object> map);

    public int modify(Map<String, Object> map);

    public int delete(Map<String, Object> map);

    public int findListCnt(Map<String, Object> map);

    public List<Map<String, Object>> findList(Map<String, Object> map);
}
