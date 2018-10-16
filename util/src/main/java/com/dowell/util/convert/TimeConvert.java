package com.dowell.util.convert;

import com.dowell.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * @author nanbo
 * @description 时间转换
 * @create 2018-10-03
 **/
public class TimeConvert implements ExportConvert {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String handler(Object val) {
		try {
			if (val == null){
				return "";
			}else {
				return DateUtils.formatCSTTime(val.toString(), "yyyy-MM-dd HH:mm:ss");
			}
		} catch (ParseException e) {
			log.error("时间转换异常", e);
			return "";
		}

	}
}
