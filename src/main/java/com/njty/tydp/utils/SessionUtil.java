package com.njty.tydp.utils;

import com.njty.tydp.commom.Subject;
import com.njty.tydp.model.UserModel;

/**
 * Created by WCL on 2018/9/3.
 */
public class SessionUtil {
    private static ThreadLocal<Subject> subjectContext = new ThreadLocal<Subject>();
    private static ThreadLocal<String> tokenContext = new ThreadLocal<String>();

    private SessionUtil(){

    }

    public static UserModel getUser(){
        UserModel user = null;
        Subject subject = getSubject();
        if(null != subject){
            user = subject.getUserModel();
        }
        return user;
    }


    public static void setSubject(Subject subject){
        subjectContext.set(subject);
    }

    public static Subject getSubject(){
        return subjectContext.get();
    }

    public static void removeSubject(){
        subjectContext.remove();
    }

    public static void setToken(String token){
        tokenContext.set(token);
    }

    public static String getToken(){
        return tokenContext.get();
    }

    public static void removeToken(){
        tokenContext.remove();
    }
}
