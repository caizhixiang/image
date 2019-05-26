package com.caizhixiang.springboot.service.enums;

/**
 * @author shaotongyao
 * @version 1.0
 * @date: 2018/05/22 15:55:30
 * @description: 异常枚举
 */
public enum ErrorCodeEnum {

	REQUEST_METHOD_ERROR("-3", "接口请求方法类型错误"),

	REQUEST_PARAMETER_IS_INCORRECT("-2", "请求参数有误"),

	INTERNAL_SERVER_ERROR("-1", "服务繁忙"),

	REQUESTED_URL_DOESNT_EXIST("-4", "请求的资源不存在"),

	OPERATION_FREQUENTLY("-5", "请求频繁，请勿重复请求"),

	SUCCESS("0", "请求成功"),

	FALSE("1", "请求失败"),

	MISSING_SYSTEM_PARAMETERS("6", "缺少系统参数"),

	PARAM_SIGN_FAILED("9", "参数验证失败"),

	REQUEST_TIME_OUT("10", "请求最大允许时间不能超过十分钟"),

	SMS_CODE_ERROR("20001", "短信验证码错误"),

	USER_NOT_REGISTER("20002", "该手机号未注册"),

	PHONE_NOT_EMPTY("20003", "手机号不能为空"),

	PHONE_NUMBER_ERROR("20004", "手机号码格式不正确"),

	USER_NOT_LOGIN("20005", "用户未登录"),


	/**************************** 新增/编辑用户信息 ***************************/
	OPERATES("1", "操作成功!"),
	FAILURE("0", "操作失败!"),
	ERROR("-1", "操作异常，请联系管理员1!"),
	UNIQUE("0", "校验参数唯一"),
	UNUNIQUE("1","校验参数不唯一"),


	UPLOADFILEERROR("80005", "上传文件失败"),


	;

	private ErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	private String errorCode;
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
