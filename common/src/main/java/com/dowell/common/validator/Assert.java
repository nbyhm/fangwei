package com.dowell.common.validator;

import com.dowell.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author nanbo
 * @description 数据效验
 * @create 2018-10-02
 **/
public abstract class Assert {
	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new BizException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new BizException(message);
		}
	}
}
