package com.njty.tydp.service.impl;

import com.njty.tydp.model.UserModel;
import com.njty.tydp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserModel findUserById(long userId) {
        return new UserModel(userId, "name..", "pwd....");
    }
}
