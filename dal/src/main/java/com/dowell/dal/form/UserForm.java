package com.dowell.dal.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

/**
 * @author nanbo
 * @description 用户表单
 * @create 2018-09-30
 **/
@ApiModel("用户表单")
public class UserForm {

	@ApiModelProperty(value = "手机号",required = true)
	@NotBlank(message="手机号不能为空")
	private String mobile;

	@ApiModelProperty(value = "密码",required = true)
	@NotBlank(message="密码不能为空")
	private String password;

	@ApiModelProperty(value = "验证码")
	private String code;

	private Boolean rememberMe;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
