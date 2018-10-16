package com.dowell.common.error;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author nanbo
 * @description 状态码
 * @create 2018-10-02
 **/
public enum StatusCodeEnum implements Serializable {
	//成功
	SUCCESS(200, "Success"),
	//错误
	ERROR(-1, "失败"),
	//警告
	WARN(1, "Warn"),
	//永久移动,资源（网页等）被永久转移到其它URL
	MOVED_PERMANENTLY(301,"Moved permanently"),
	//失败
	FAIL(400, "请求出错"),
	//未授权
	UNAUTHORIZED(401, "未授权"),
	//禁止访问
	DISABLE_ACCESS(403, "禁止访问"),
	//未找到资源
	NOT_FOUUND(404, "Resource not found"),
	//不允许此方法
	METHOD_NOT_ALLOWED(405, "Not allowed method"),
	//不可接受
	UNACCEPTABLE(406, "不可接受"),
	//服务器内部错误
	INTERNAL_ERROR(500,"Server internal error"),
	//无效参数
	INVALID_ARGUMENT(11001, "无效参数"),
	//用户不存在
	USER_NOT_EXIST(21002, "获取用户失败"),
	//无效用户
	USER_INVALID(60000, "无效用户"),
	//未知异常
	UNKNOWN(-1, "Unknown error");

	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;

	StatusCodeEnum(int code, String msg) {
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

	//根据code获取对应枚举
	public static StatusCodeEnum getByCode(int code) {
		StatusCodeEnum[] values = StatusCodeEnum.values();

		for (StatusCodeEnum bizStatusCodeEnum : values) {
			if (bizStatusCodeEnum.code == code) {
				return bizStatusCodeEnum;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
