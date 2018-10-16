package com.dowell.common.exception;

import com.dowell.common.error.StatusCodeEnum;

/**
 * @author nanbo
 * @description 自定义异常
 * @create 2018-10-02
 **/
public class BizException extends RuntimeException {
	private String msg;
	private int code = 500;

	public BizException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public BizException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public BizException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BizException(int code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
