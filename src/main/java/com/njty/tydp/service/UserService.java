package com.njty.tydp.service;

import com.njty.tydp.model.UserModel;

public interface UserService {

    UserModel findUserById(long userId);
}
