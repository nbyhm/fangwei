package com.dowell.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nanbo
 * @description 相应结果，主控制层，是用户与数据交互的核心中转站，控制用户数据收集，控制请求转向
 * @create 2018-10-02
 **/
public class ResponseBo extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public ResponseBo() {
		put("code", 0);
		put("msg", "success");
	}

	/**
	 * 失败：返回默认失败消息
	 * @return
	 */
	public static ResponseBo error() {
		return error(500, "未知异常，请联系管理员");
	}

	/**
	 * 失败：返回指定内容
	 * @param msg 消息
	 * @return
	 */
	public static ResponseBo error(String msg) {
		return error(500, msg);
	}

	/**
	 * 失败：指定code、msg
	 * @param code 错误码
	 * @param msg  消息
	 * @return
	 */
	public static ResponseBo error(int code, String msg) {
		ResponseBo r = new ResponseBo();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	/**
	 * 警告：返回警告信息
	 * @param msg
	 * @return
	 */
	public static ResponseBo warn(Object msg) {
		ResponseBo r = new ResponseBo();
		r.put("code", 1);
		r.put("msg", msg);
		return r;
	}

	public static ResponseBo ok(String msg) {
		ResponseBo r = new ResponseBo();
		r.put("msg", msg);
		return r;
	}

	public static ResponseBo ok(Map<String, Object> map) {
		ResponseBo r = new ResponseBo();
		r.putAll(map);
		return r;
	}

	public static ResponseBo ok() {
		return new ResponseBo();
	}

	@Override
	public ResponseBo put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
