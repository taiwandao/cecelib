package com.isat.lib.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import test.isat.com.cecelib.R;

public class StringUtil {

	private static final String TAG = "StringUtil";

	static Pattern mobilePattern = Pattern
			.compile("^((13[0-9])|(15[0-9])|(18[0-9]|(17[0-9])))\\d{8}$");
	static Pattern emailPattern = Pattern
			.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");

	static DecimalFormat dfIn6FractionDigits = new DecimalFormat("0.000000");

	static DecimalFormat dfIn2FractionDigits = new DecimalFormat("0.00");

	static DecimalFormat dfIn1FractionDigits = new DecimalFormat("0.0");

	static DecimalFormat dfIn0FractionDigits = new DecimalFormat("0");

	public static boolean isEmail(String email) {
		Matcher m = emailPattern.matcher(email);
		return m.matches();
	}

	public static boolean isMobile(String mobile) {
		Matcher matcher = mobilePattern.matcher(mobile);
		return matcher.matches();
	}



	/**
	 * 根据Unicode编码完美的判断中文汉字和符号
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 完整的判断中文汉字和符号
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return context.getString(R.string.can_not_find_version_name);
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			int version = info.versionCode;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 清楚特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		String regEx = "[^a-zA-Z]";
		// 清除掉所有特殊字符
		// String regEx =
		// "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 反转字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseStr(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	@SuppressLint("DefaultLocale")
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}

	/**
	 * 数字转成字母
	 * 
	 * @param number
	 * @return
	 */
	public static String number2Word(String number) {
		if ("1".equals(number)) {
			return "A";
		} else if ("2".equals(number)) {
			return "B";
		} else if ("3".equals(number)) {
			return "C";
		} else if ("4".equals(number)) {
			return "D";
		} else if ("5".equals(number)) {
			return "E";
		}
		return "";
	}

	/**
	 * 自动补位
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append(str).append("&nbsp;");// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	/**
	 * 获取当前语言环境
	 * 
	 * @return
	 */
	public static String getSystemLanguage(Context ctx) {
		Locale locale = ctx
				.getResources().getConfiguration().locale;
		return locale.getLanguage();
	}

	/**
	 * 查詢一個字符串在另一個字符串中出現的次數
	 * 
	 * @param str
	 * @param substr
	 * @return
	 */
	public static int calculate(String str, String substr) {
		String temp = str;
		int count = 0;
		int index = temp.indexOf(substr);
		while (index != -1) {
			temp = temp.substring(index + 1);
			index = temp.indexOf(substr);
			count++;
		}
		return count;
	}

	public static boolean isNotNull(String str) {
		if (str != null && !TextUtils.isEmpty(str))
			return true;
		return false;
	}

	public static int printStatistics(String s) {
		String[] words = s.replaceAll("[^a-zA-Z1-9]+", " ").trim().split(" ");

		if (words.length > 0) {
			double totalLengthOfWords = 0;
			TreeSet<Integer> lengths = new TreeSet<Integer>();

			for (String word : words) {
				lengths.add(word.length());
				totalLengthOfWords += word.length();
			}

			System.out.println("单词总数：" + words.length + "\n最长长度："
					+ lengths.last() + "\n最短长度：" + lengths.first() + "\n平均长度："
					+ totalLengthOfWords / words.length);
		} else
			System.out.println("没有单词");

		return words.length;
	}

	/**
	 * 单词字符排序
	 * 
	 * @param ans
	 * @return
	 */
	public static String WordArraysOrder(String ans) {
		if (!TextUtils.isEmpty(ans)) {
			String[] words = new String[ans.length()];
			for (int i = 0; i < ans.length(); i++) {
				words[i] = ans.substring(i, i + 1);
			}
			Arrays.sort(words);
			StringBuffer stringBuffer = new StringBuffer("");
			for (int i = 0; i < words.length; i++) {
				stringBuffer.append(words[i]);
			}
			return stringBuffer.toString();
		} else {
			return "";
		}

	}

	/**
	 * 获取百分比
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int percent(double p1, double p2) {
		if (p2 == 0)
			return 0;
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(0);
		str = nf.format(p3);
		return Integer.valueOf(str.replace("%", ""));
	}
}
