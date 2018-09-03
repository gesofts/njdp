package com.njty.tydp.authenticator.impl;

import com.njty.tydp.authenticator.Authenticator;
import com.njty.tydp.commom.ErrCode;
import com.njty.tydp.model.MsgModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

public class AuthenticateChain implements Authenticator {

    private List<Authenticator> authenticators = new ArrayList<Authenticator>();

    public AuthenticateChain addAuthenticator(Authenticator authenticator){
        this.authenticators.add(authenticator);
        return this;
    }

    @Override
    public MsgModel authenticate(String token) {
        System.out.println(">>>>>>>>>>>>>>>>>进入AuthenticateChain，入参 token=" + token);
        MsgModel MsgModel = null;
        //token 为空直接返回错误
        if(StringUtils.isBlank(token)){
            System.out.println(">>>>>>>>>>>>>>>>>AuthenticateChain， token为空，返回错误信息");
            return MsgModel.getErrRet(ErrCode.AUTHTICATION_TOKEN_ERROR.getCode(),ErrCode.AUTHTICATION_TOKEN_ERROR.getMsg());
        }

        token = new String(Base64Utils.decodeFromString(token));
        String[] tokenMembers = token.split(":");

        String userId = tokenMembers[0];
        if(StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)){
            return MsgModel.getErrRet(ErrCode.AUTHTICATION_TOKEN_ERROR.getCode(),ErrCode.AUTHTICATION_TOKEN_ERROR.getMsg());
        }

        String expireStr = tokenMembers[2];
        if(StringUtils.isBlank(expireStr) || !StringUtils.isNumeric(expireStr)){
            return MsgModel.getErrRet(ErrCode.AUTHTICATION_TOKEN_ERROR.getCode(),ErrCode.AUTHTICATION_TOKEN_ERROR.getMsg());
        }

        long expire = Long.valueOf(expireStr);
        //验证 token 是否过期
        if(0 < (System.currentTimeMillis() - expire)){
            return MsgModel.getErrRet(ErrCode.AUTHTICATION_TOKEN_EXPIRE.getCode(),ErrCode.AUTHTICATION_TOKEN_EXPIRE.getMsg());
        }

        for(Authenticator authenticator:authenticators){
            MsgModel = authenticator.authenticate(token);
            if(MsgModel.isSuccess()){
                break;
            }
        }

        return MsgModel;
    }
}
