package com.caizhixiang.springboot.exception;


import com.caizhixiang.springboot.service.enums.ErrorCodeEnum;
import lombok.ToString;

/**
 * @author shaotongyao
 * @version 1.0
 * @date: 2018/05/22 15:55:30
 * Description: 业务异常
 */

@ToString
public class BizException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5110371239340231002L;

    private String errorCode;

    private String errorMsg;

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Exception exception) {
        super(exception);
    }

    public BizException(ErrorCodeEnum errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
    }

    public BizException(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(ErrorCodeEnum errorCode) {
        super();
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
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


}
