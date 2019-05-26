package com.caizhixiang.springboot.web.vo;


import com.caizhixiang.springboot.service.enums.ErrorCodeEnum;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 
 * @desc 
 * 
 * @author shaotongyao
 *
 * @param <T>
 */
public class ApiResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;
	
	private T data;
	

	public ApiResult() {
		super();
		this.errorCode = ErrorCodeEnum.SUCCESS.getErrorCode();
		this.errorMsg = ErrorCodeEnum.SUCCESS.getErrorMsg();

	}
    public ApiResult(T data) {
        if (ObjectUtils.isEmpty(data)) {
        	this.errorCode = ErrorCodeEnum.SUCCESS.getErrorCode();
    		this.errorMsg = ErrorCodeEnum.SUCCESS.getErrorMsg();
        } else {
        	this.errorCode = ErrorCodeEnum.SUCCESS.getErrorCode();
    		this.errorMsg = ErrorCodeEnum.SUCCESS.getErrorMsg();
            this.data = data;
        }
    }
	public ApiResult(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public ApiResult(ErrorCodeEnum codeEnum) {
		super();
		this.errorCode = codeEnum.getErrorCode();
		this.errorMsg = codeEnum.getErrorMsg();
	}
	public ApiResult(String errorCode, String errorMsg, T data) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

	
}