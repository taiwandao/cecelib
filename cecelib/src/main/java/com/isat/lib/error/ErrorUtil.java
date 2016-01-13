package com.isat.lib.error;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.isat.lib.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import test.isat.com.cecelib.R;

public class ErrorUtil {
	final static String TAG = "ErrorUtil";

	public static Map<String, Integer> error2TextRes = new HashMap<String, Integer>();
	static {
		error2TextRes.put(ExecWithErrorCode.DB_ERROR, R.string.error_db);
		error2TextRes.put(ExecWithErrorCode.NETWORK_ERROR,
				R.string.error_network);
		error2TextRes.put(ExecWithErrorCode.AUTH_ERROR, R.string.error_auth);
		error2TextRes.put(ExecWithErrorCode.SERVER_ACCOUNT_NOEXIST,
				R.string.login_account_noexist);
		error2TextRes.put(ExecWithErrorCode.SERVER_PWD_ERROR,
				R.string.login_pwd_error);
		error2TextRes.put(ExecWithErrorCode.SERVER_VERFCODE_ERROR,
				R.string.error_verfcode);
		error2TextRes.put(ExecWithErrorCode.SERVER_RESPONSE_ERROR,
				R.string.error_server_response);
		error2TextRes.put(ExecWithErrorCode.ARGUMENTS_USER_PWD_NULL_ERROR,
				R.string.username_pwd_not_null);
		error2TextRes.put(ExecWithErrorCode.ARGUMENTS_USER_PWD_ERROR,
				R.string.username_pwd_error);
		error2TextRes.put(ExecWithErrorCode.ARGUMENTS_OLD_PWD_ERROR,
				R.string.error_account_old_pwd);
		error2TextRes.put(ExecWithErrorCode.ERROR_CUSTOM_MSG,
				R.string.error_network);
		error2TextRes.put(ExecWithErrorCode.NETWORK_SYSTEM_MAINTENANCE_ERROR,
				R.string.system_maintenance);
		
	}

	public static CharSequence getErrorText(Context context, ExecWithErrorCode e) {
		if (error2TextRes.get(e.getErrorCode()) != null) {
			// 已定义错误码的异常
			return context.getResources().getText(
					error2TextRes.get(e.getErrorCode()));
		} else {
			LogUtil.e(TAG, e, e.getMessage());
			return context.getResources().getText(R.string.error_common);
		}
	}

	public static CharSequence getErrorText(Context context, Exception e) {
		if (e instanceof ExecWithErrorCode) {
			// 已处理的异常
			return getErrorText(context, (ExecWithErrorCode) e);
		} else {
			// 未知异常
			LogUtil.e(TAG, e, e.getMessage());
			return context.getResources().getText(R.string.error_common);
		}
	}

	public static CharSequence getErrorText(Context context, int resId) {
		return context.getResources().getText(resId);
	}

	public static void makeToast(Context context, ExecWithErrorCode e) {
		makeToast(context, getErrorText(context, e), Toast.LENGTH_SHORT);
	}

	public static void makeToast(Context context, int resId, int duration) {
		makeToast(context, context.getResources().getText(resId), duration);
	}
	
	public static void makeToast(Context context, int resId) {
		makeToast(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
	}

	public static void makeToast(Context context, CharSequence text) {
		makeToast(context, text, Toast.LENGTH_SHORT);
	}
	
	public static void makeToast(Context context, CharSequence text, int duration) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.view_toast,
				null);

		Toast toast = new Toast(context);
		
		TextView textView = (TextView) view
				.findViewById(R.id.view_toast_textview);
		textView.setText(text);
		toast.setView(view);
		// 居中显示
		toast.setGravity(Gravity.CENTER, 0, 0);

		toast.setDuration(duration);
		toast.show();
	}
}
