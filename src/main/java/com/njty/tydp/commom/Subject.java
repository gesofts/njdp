package com.njty.tydp.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.njty.tydp.model.UserModel;

/**
 * Created by WCL on 2018/9/3.
 */
public class Subject {
    private UserModel userModel;
    boolean isAuthenticated;

    public Subject(UserModel userModel) {
        this.userModel = userModel;
    }

    public Subject(UserModel userModel, boolean isAuthenticated) {
        this(userModel);
        this.isAuthenticated = isAuthenticated;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
