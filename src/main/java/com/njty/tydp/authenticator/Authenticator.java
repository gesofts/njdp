package com.njty.tydp.authenticator;

import com.njty.tydp.model.MsgModel;

public interface Authenticator {
    MsgModel authenticate(String token);
}
