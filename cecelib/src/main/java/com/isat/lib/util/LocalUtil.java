package com.isat.lib.util;

import java.util.Locale;

/**
 * 地区
 * @author yf
 *
 */
public class LocalUtil {
	
	public static String getCountry(){
		Locale locale = Locale.getDefault();  
		String region = locale.getCountry();
		return region;
	}
	
	public static String getLanguage(){
		Locale locale = Locale.getDefault();  
		String language = locale.getCountry();
		return language;
	}
	
}
