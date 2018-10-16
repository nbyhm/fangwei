package com.dowell.dal.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.log4j.Log4j;

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
}
