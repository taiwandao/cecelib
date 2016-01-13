package com.isat.lib.error;

public class ExecWithErrorCode extends Exception {

	private static final long serialVersionUID = -4226805851594530829L;

	// 数据库错误
	public static final String DB_ERROR = "1";
	// 网络错误
	public static final String NETWORK_ERROR = "3";
	// 系统维护
	public static final String NETWORK_SYSTEM_MAINTENANCE_ERROR = "303";
	// 参数错误
	public static final String ARGUMENTS_ERROR = "4";
	// 鉴权失败
	public static final String AUTH_ERROR = "401";
	//用户名或密码为空
	public static final String ARGUMENTS_USER_PWD_NULL_ERROR = "403";
	//用户名密码错误
	public static final String ARGUMENTS_USER_PWD_ERROR = "404";
	//自定义消息 服务器返回提示消息
	public static final String ERROR_CUSTOM_MSG = "6";
	//服务器响应异常
	public static final String SERVER_RESPONSE_ERROR = "402";
	//旧密码错误
	public static final String ARGUMENTS_OLD_PWD_ERROR = "405";
	// 账号不存在
	public static final String SERVER_ACCOUNT_NOEXIST = "4001000002";
	// 密码验证失败
	public static final String SERVER_PWD_ERROR = "4001000003";
	// 验证码验证失败
	public static final String SERVER_VERFCODE_ERROR = "4001000011";
	//练习前必须测试
	public static final String TOPIC_EXERCIES_MUST_TEST = "04-3000-1001";
	//没有题目了
	public static final String TOPIC_EXERCIES_NO_QUESTION = "502";
	public static final String TOPIC_EXERCIES_NO_QUESTION2 = "04-3000-1007";
	public static final String QQ_BIND_NO = "04-2000-1023";
	public static final String PWD_ERROR = "04-2000-1001";
	
	private String errorCode;
	private String errorMessage;
	public ExecWithErrorCode(Exception e) {
		super(e);
	}

	public ExecWithErrorCode(String errorCode, String message) {
		super(message);
		if (errorCode == null) {
			throw new NullPointerException("errorCode is null");
		}
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	public ExecWithErrorCode(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "[" + errorCode + "]" + super.toString();
	}

}
