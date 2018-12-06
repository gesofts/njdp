/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EntityServiceImpl
 * Author:   wangcl
 * Date:     2018/12/6 下午2:17
 * Description: 基础
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.njty.tydp.commom;

import com.njty.tydp.model.MsgModel;

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
public abstract class EntityServiceImpl {

    public int add(Map<String, Object> map){
        return  getEntityMapper().add(map);
    }

    public int modify(Map<String, Object> map){
        return  getEntityMapper().modify(map);
    }

    public int delete(Map<String, Object> map){
        return  getEntityMapper().delete(map);
    }

    public MsgModel findList(Map<String, Object> map) {
        MsgModel msgModel = new MsgModel();
        msgModel.setTotal(getEntityMapper().findListCnt(map));
        msgModel.setData(getEntityMapper().findList(map));
        return msgModel;
    }

    public abstract EntityMapper getEntityMapper();
}
