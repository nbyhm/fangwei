package com.dowell.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author nanbo
 * @description
 * @create 2018-10-03
 **/
public class FileUtils {
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);

	private FileUtils() {

	}

	/**
	 * 文件名加UUID
	 *
	 * @param filename 文件名
	 * @return UUID_文件名
	 */
	public static String makeFileName(String filename) {
		//生成uuid
		return UUID.randomUUID().toString().replaceAll("-","") + "_" + filename;
	}

	/**
	 * 文件名特殊字符过滤
	 *
	 * @param fileName 文件名
	 * @return 过滤后的文件名
	 * @throws PatternSyntaxException 正则异常
	 */
	public static String stringFilter(String fileName) {
		String regEx = "[`~!@#$%^&*+=|{}':; ',//[//]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(fileName);
		return m.replaceAll("").trim();
	}

}
