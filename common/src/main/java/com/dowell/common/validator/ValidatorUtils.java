package com.dowell.common.validator;

import com.dowell.common.exception.BizException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author nanbo
 * @description hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @create 2018-10-02
 **/
public class ValidatorUtils {
	private static Validator validator;

	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 校验对象
	 * @param object        待校验对象
	 * @param groups        待校验的组
	 * @throws BizException  校验不通过，则报RRException异常
	 */
	public static void validateEntity(Object object, Class<?>... groups)
			throws BizException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
			throw new BizException(constraint.getMessage());
		}
	}

}
