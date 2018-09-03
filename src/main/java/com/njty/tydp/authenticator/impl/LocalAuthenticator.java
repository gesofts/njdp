package com.njty.tydp.authenticator.impl;

import com.njty.tydp.authenticator.Authenticator;
import com.njty.tydp.utils.ApplicationContextUtil;
import com.njty.tydp.commom.ErrCode;
import com.njty.tydp.commom.Subject;
import com.njty.tydp.model.MsgModel;
import com.njty.tydp.model.UserModel;
import com.njty.tydp.service.UserService;
import com.njty.tydp.utils.DigestUtil;
import com.njty.tydp.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;


public class LocalAuthenticator implements Authenticator {

	private UserService userService;

	public LocalAuthenticator(){
		this.userService = (UserService) ApplicationContextUtil.getBean("userServiceImpl");
	}

	@Override
	public MsgModel authenticate(String token) {
		MsgModel msgModel = MsgModel.getErrRet(ErrCode.AUTHTICATION_TOKEN_ERROR.getCode(),ErrCode.AUTHTICATION_TOKEN_ERROR.getMsg());

		String[] tokenMembers = token.split(":");
		long userId = Long.valueOf(tokenMembers[0]);
		String md5 = tokenMembers[1];
		long expire = Long.valueOf(tokenMembers[2]);

		UserModel mUserModel = this.userService.findUserById(userId);
		if(null == mUserModel){
		    return msgModel;
        }

		String loginName = mUserModel.getLoginName();
		String password = mUserModel.getPassword();
		String realMd5 = DigestUtil.md5(String.valueOf(userId),loginName,password,String.valueOf(expire));

		if(!StringUtils.equals(md5,realMd5)){
			return msgModel;
		}

		this.bindUserToSession(mUserModel);

		return MsgModel.getSuccessRet(null);
	}

	private void bindUserToSession(UserModel userModel) {
		Subject subject = new Subject(userModel, true);
		SessionUtil.setSubject(subject);
	}
}
