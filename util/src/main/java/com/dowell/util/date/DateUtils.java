package com.dowell.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author nanbo
 * @description 时间工具类
 * @create 2018-10-03
 **/
public class DateUtils {
	private DateUtils(){

	}

	private static String getDateFormat(Date date, String dateFormatType) {
		SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
		return simformat.format(date);
	}

	public static String formatCSTTime(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date d = sdf.parse(date);
		return DateUtils.getDateFormat(d, format);
	}
}
