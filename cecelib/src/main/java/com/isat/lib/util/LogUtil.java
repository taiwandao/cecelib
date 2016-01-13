/**
 * 
 * 文 件 名:  LogUtil.java
 * 描    述:  日志记录工具类
 * 
 */
package com.isat.lib.util;

import android.util.Log;


public class LogUtil {

	/**
	 * 是否启用调试模式, 如果为false不记录任何日志
	 */
	public static final boolean ADB = true;

	/**
	 * 日志级别
	 */
	private static final int LOG_DEGREE = Log.VERBOSE;

	public static boolean isDebugable() {
		return ADB && LOG_DEGREE <= Log.DEBUG;
	}

	public static boolean isInfoable() {
		return ADB && LOG_DEGREE <= Log.INFO;
	}

	public static boolean isWarnable() {
		return ADB && LOG_DEGREE <= Log.WARN;
	}

	public static boolean isErrorable() {
		return ADB && LOG_DEGREE <= Log.ERROR;
	}


	public static void v(String tag, String msg) {
		if (ADB && LOG_DEGREE <= Log.VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (ADB && LOG_DEGREE <= Log.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (ADB && LOG_DEGREE <= Log.INFO) {
			Log.i(tag, msg);
		}
	}


	public static void w(String tag, String msg) {
		if (ADB && LOG_DEGREE <= Log.WARN) {
			Log.w(tag, msg);
		}
	}
	
	public static void e(String tag, Exception e) {
		if (ADB && LOG_DEGREE <= Log.ERROR) {
			Log.e(tag, Log.getStackTraceString(e));
		}
	}

	public static void e(String tag, String msg) {
		if (ADB && LOG_DEGREE <= Log.ERROR) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, Throwable tr, String msg) {
		if (ADB && LOG_DEGREE <= Log.ERROR) {
			Log.e(tag, msg, tr);
		}
	}
}
