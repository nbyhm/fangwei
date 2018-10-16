package com.dowell.service.excel;

import com.dowell.common.result.ResponseBo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description Excel
 * @create 2018-10-03
 **/
public interface ExcelService {

	/**
	 * 生成Excel文件
	 * @param filename  文件名
	 * @param list      文件内容
	 * @param clazz     List中的对象类型
	 * @return          ResponseBo
	 */
	ResponseBo createExcelByPoiKit(String filename, List<?> list, Class<?> clazz);

	/**
	 * 生成Csv文件
	 * @param filename 文件名
	 * @param list     文件内容
	 * @param clazz    List中的对象类型
	 * @return         ResponseBo
	 */
	ResponseBo createCsv(String filename, List<?> list, Class<?> clazz);

	/**
	 * 读取Excel数据
	 * @param fis
	 * @param filePath 文件路径
	 * @param filename 文件名
	 * @return
	 */
	List<Map<String, Object>> readExcelData(InputStream fis, String filePath, String filename);

	/**
	 * 读取Excel中物流码数据
	 * @param fis
	 * @param fileName 文件名
	 * @return
	 */
	List<Map<String, List<String>>> readExcelLogisticsCodeData(InputStream fis, String fileName);
}
