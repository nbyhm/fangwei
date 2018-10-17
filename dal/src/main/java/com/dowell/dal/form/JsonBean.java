package com.dowell.dal.form;

/**
 * @author nanbo
 * @description
 * @create 2018-10-17
 **/
public class JsonBean {
	/**
	 * code : string
	 * mobile : string
	 * password : string
	 * rememberMe : true
	 */

	private String code;
	private String mobile;
	private String password;
	private boolean rememberMe;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
