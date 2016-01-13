package com.isat.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

public class VerificationUtil {


	/**
	 * 验证用户账号是否合法
	 * 
	 * @param account
	 * @return true:合法
	 */
	public static boolean valideAccount(String account) {
		return (!TextUtils.isEmpty(account))
				&& (StringUtil.isEmail(account) || StringUtil.isMobile(account));
	}

	/**
	 * 验证密码是否合法
	 * 
	 * @param pwd
	 * @return true:合法
	 */
	public static boolean validePassword(String pwd) {
		return (!TextUtils.isEmpty(pwd)) && pwd.length() >= 1
				&& pwd.length() <= 20;
	}

	/**
	 * 验证码是否合法
	 * 
	 * @param verfCode
	 * @return true:合法
	 */
	public static boolean valideVerfCode(String verfCode) {
		return (!TextUtils.isEmpty(verfCode)) && verfCode.length() == 6;
	}

	/**
	 * 判断是否是rainbow计步器
	 * 
	 * @param deviceName
	 * @return
	 */
	public static boolean isRainbowPetemeter(String deviceName) {
		if (deviceName.indexOf("ibod") > -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 对网络连接状态进行判断
	 * 
	 * @return true, 可用； false， 不可用
	 */
	public static boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager.getActiveNetworkInfo() != null) {
			return connManager.getActiveNetworkInfo().isAvailable();
		}

		return false;
	}
	
	// 判断手机号
	public static boolean isMobileNO(String mobiles) {
		if (mobiles == null) {
			return false;
		} else {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles.replaceAll(" ", ""));
			return m.matches();
		}
	}

	// 判断验证码是否是有效数字
	public static boolean isRightverify(String code) {
		boolean b = true;
		int length = code.trim().length();
		if (length == 6) {
			try {
				Integer.parseInt(code);
			} catch (Exception e) {
				b = false;
			}
		} else {
			b = false;
		}
		return b;

	}
	
	public static int getSDKVersion(){
		return android.os.Build.VERSION.SDK_INT;
	}
	/**
	 * 判断是否大于3.0
	 * @return
	 */
	public static boolean isAboveHoneycomb(){
		if(getSDKVersion() >= 11){
			return true;
		}
		return false;
	}
}
