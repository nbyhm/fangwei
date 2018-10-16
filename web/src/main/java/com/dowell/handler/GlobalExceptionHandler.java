package com.dowell.handler;

import com.dowell.common.exception.BizException;
import com.dowell.common.result.ResponseBo;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author nanbo
 * @description 全局异常处理程序
 * @create 2018-10-01
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 *  拦截捕捉自定义异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	public ResponseBo handlerBizException(BizException e){
		ResponseBo responseBo = new ResponseBo();
		responseBo.put("code", e.getCode());
		responseBo.put("msg", e.getMessage());

		return responseBo;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseBo handlerDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return ResponseBo.error("数据库已存在该记录");
	}

	/**
	 * 全局异常（Exception）捕获处理，一定要捕获,不然项目启动不了
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseBo handlerException(Exception e){
		log.error(e.getMessage(), e);
		return ResponseBo.error();
	}

}
