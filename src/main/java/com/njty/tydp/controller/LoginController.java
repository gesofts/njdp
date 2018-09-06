package com.njty.tydp.controller;

import com.njty.tydp.model.MsgModel;
import com.njty.tydp.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by WCL on 2018/9/6.
 */
@RestController
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public MsgModel login(UserModel model) {

        //UserModel model = new UserModel();
        logger.info("LoginController - login");
        MsgModel msgModel = new MsgModel();
        msgModel.setData(model);
        return  msgModel;
    }


    @RequestMapping("/login2")
    public MsgModel login2(@RequestParam Map<String, String> paramMap) {
        logger.info("LoginController - login");
        MsgModel msgModel = new MsgModel();
        msgModel.setData(paramMap);
        return  msgModel;
    }

}
