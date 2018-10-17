package com.dowell.config;

import com.dowell.common.domain.ValidateCodeProperties;

/**
 * @author nanbo
 * @description
 * @create 2018-10-18
 **/
public class NoahProperties {

	private ValidateCodeProperties validateCode = new ValidateCodeProperties();

	private String timeFormat = "yyyy-MM-dd HH:mm:ss";

	private boolean openAopLog = true;

	public ValidateCodeProperties getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(ValidateCodeProperties validateCode) {
		this.validateCode = validateCode;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public boolean isOpenAopLog() {
		return openAopLog;
	}

	public void setOpenAopLog(boolean openAopLog) {
		this.openAopLog = openAopLog;
	}
}
