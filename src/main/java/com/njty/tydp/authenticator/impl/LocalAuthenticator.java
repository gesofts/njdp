package com.njty.tydp.authenticator.impl;

import com.njty.tydp.authenticator.Authenticator;
import com.njty.tydp.commom.ApplicationContextUtil;
import com.njty.tydp.commom.ErrCode;
import com.njty.tydp.model.MsgModel;
import com.njty.tydp.model.UserModel;
import com.njty.tydp.service.UserService;
import com.njty.tydp.utils.DigestUtil;
import com.xingyi.logistic.authentication.authenticator.Authenticator;
import com.xingyi.logistic.authentication.model.LocalAuth;
import com.xingyi.logistic.authentication.model.UserProfile;
import com.xingyi.logistic.authentication.security.Subject;
import com.xingyi.logistic.authentication.security.User;
import com.xingyi.logistic.authentication.service.LocalAuthService;
import com.xingyi.logistic.authentication.service.UserProfileService;
import com.xingyi.logistic.authentication.util.ApplicationContextUtil;
import com.xingyi.logistic.authentication.util.DigestUtil;
import com.xingyi.logistic.authentication.util.SessionUtil;
import com.xingyi.logistic.common.bean.ErrCode;
import com.xingyi.logistic.common.bean.JsonRet;
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

		this.bindUserToSession(userId);

		return MsgModel.getSuccessRet(null);
	}

	private void bindUserToSession(Long userId) {
		JsonRet<UserProfile> profileJsonRet = this.userProfileService.getById(userId);
		UserProfile profile = profileJsonRet.getData();
		User user = new User(profile);
		Subject subject = new Subject(user, true);
		SessionUtil.setSubject(subject);
	}
}
