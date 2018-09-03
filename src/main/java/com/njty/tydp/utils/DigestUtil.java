package com.njty.tydp.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

public final class DigestUtil {
	private DigestUtil(){

	}

	public static String md5(String... src){
		if(ArrayUtils.isEmpty(src)){
			return null;
		}

		char[] chars = new char[]{};
		for(String s:src){
			if(null == s){
				s = "null";
			}
			chars = ArrayUtils.addAll(chars,s.toCharArray());
		}

		Arrays.sort(chars);

		String md5 = DigestUtils.md5DigestAsHex(new String(chars).getBytes());
		return md5;
	}
}