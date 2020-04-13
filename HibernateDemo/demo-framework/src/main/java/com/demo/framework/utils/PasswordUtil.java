package com.demo.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PasswordUtil {

	public final static int PASSWORD_SALT_LENGTH = 10;

	/**
	 * 获取一个指定长度的数字字符串
	 * 
	 * @param length 返回随机数的长度
	 * @return 字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		sb.append(sd.format(new Date()));

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * 盐值加密
	 * 
	 * @param currentPassword
	 * @param passwordSalt
	 * @return 字符串
	 */
	public static String passwordToPasswordSalt(String currentPassword, String passwordSalt) {
		return MD5andKLUtil.MD5(currentPassword + passwordSalt);
	}

	public static String getSecretKey() {
		return generateString(PASSWORD_SALT_LENGTH);
	}
}
