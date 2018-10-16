package com.dowell.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author nanbo
 * @description 读取excel表的数据返回List<Map<String, Object>>
 * @create 2018-10-04
 **/
public class PutExcelToData {
	//开始行号（第二行开始）
	static int startrow = 1;
	//开始列号（第一列开始）
	static int startcol = 0;
	static int sheetNum = 0;

	/**
	 * @methodName putExcel
	 * @description 读取excel数据生成List<Map<String,Object>>
	 * @param filePath 文件目录路径
	 * @param fileName  文件名
	 * @return  List<Map<String,Object>>
	 */
	public static List<Map<String,Object>> putExcel(InputStream inputStream, String filePath, String fileName){
		List<Map<String,Object>> varList=new ArrayList<>();
		//得到服务器上excel文件
		//File excelFile=new File("D:/Document/4月9日物流单号-test.xlsx");
		//得到文件流
		//FileInputStream fis=null;
		try {
			//fis=new FileInputStream(excelFile);
			//poi api读取流
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			//sheet 从0开始
			HSSFSheet sheet = wb.getSheetAt(sheetNum);
			//取得最后一行的行号（遍历行）
			int rowNum = sheet.getLastRowNum() + 1;
			for (int i = startrow; i < rowNum; i++) {
				//PageData varmap = new PageData();
				Map<String,Object> varmap=new HashMap<String, Object>();
				//得到一行
				HSSFRow row = sheet.getRow(i);
				//每行的最后一个单元格位置（遍历列）
				int cellNum = row.getLastCellNum();
				//列循环开始
				for (int j = startcol; j < cellNum; j++) {
					HSSFCell cell = row.getCell(j);
					String cellValue = null;
					if (null != cell) {
						// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						switch (cell.getCellType()) {
							case 0:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
									SimpleDateFormat sdf = null;
									if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
											.getBuiltinFormat("h:mm")) {
										sdf = new SimpleDateFormat("HH:mm");
									} else if(22 == cell.getCellStyle().getDataFormat()){// 日期
										sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									} else if(184 == cell.getCellStyle().getDataFormat()){
										sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									} else {
										sdf = new SimpleDateFormat("yyyy-MM-dd");
									}
									Date date = cell.getDateCellValue();
									cellValue = sdf.format(date);
								} else if (cell.getCellStyle().getDataFormat() == 58) {
									// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
									cellValue = sdf.format(date);
								} else {
									DecimalFormat df = new DecimalFormat("0");
									cellValue = df.format(cell.getNumericCellValue());
								}

								break;
							case 1:
								String str = cell.getStringCellValue();
								cellValue = str.replace(".0","");
								break;
							case 2:
								cellValue = cell.getNumericCellValue() + "";
								break;
							case 3:
								cellValue = "";
								break;
							case 4:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case 5:
								cellValue = String.valueOf(cell.getErrorCellValue());
								break;
						}

					}else{
						cellValue = "";
					}
					varmap.put("var"+j, cellValue);

				}
				varList.add(varmap);
			}
		}catch (Exception e) {
			//varList = readExcelForXlsx(inputStream ,filePath, filename, startrow, startcol, sheetNum);
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return varList;

	}

	/**
	 * @methodName readExcelForXlsx
	 * @description 如果excel文件是xlsx文件，调用这个方法
	 * @return
	 */
	public static List<Map<String,Object>> readExcelForXlsx(InputStream inputStream, String filePath, String fileName) {
		List<Map<String,Object>> varList = new ArrayList<>();
		//FileInputStream fi = null;
		try {
			//File target = new File("D:/Document/01.xlsx");
			//fi = new FileInputStream(target);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			//sheet 从0开始
			XSSFSheet sheet = wb.getSheetAt(sheetNum);
			//取得最后一行的行号
			int rowNum = sheet.getLastRowNum() + 1;
			//行循环开始
			for (int i = startrow; i < rowNum; i++) {
				Map<String,Object> varmap = new HashMap<String, Object>();
				//行
				XSSFRow row = sheet.getRow(i);
				//每行的最后一个单元格位置
				int cellNum = row.getLastCellNum();
				//列循环开始
				for (int j = startcol; j < cellNum; j++) {
					XSSFCell cell = row.getCell(j);
					String cellValue = null;
					if (null != cell) {
						// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						switch (cell.getCellType()) {
							case 0:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
									SimpleDateFormat sdf = null;
									if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
											.getBuiltinFormat("h:mm")) {
										sdf = new SimpleDateFormat("HH:mm");
									} else if(22 == cell.getCellStyle().getDataFormat()){// 日期
										sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									} else if(184 == cell.getCellStyle().getDataFormat()){
										sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									} else {
										sdf = new SimpleDateFormat("yyyy-MM-dd");
									}
									Date date = cell.getDateCellValue();
									cellValue = sdf.format(date);
								} else if (cell.getCellStyle().getDataFormat() == 58) {
									// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
									cellValue = sdf.format(date);
								} else {
									double value = cell.getNumericCellValue();
//								CellStyle style = cell.getCellStyle();
//								DecimalFormat format = new DecimalFormat();
//								String temp = style.getDataFormatString();
//								// 单元格设置成常规
//								if (temp.equals("General")) {
//									format.applyPattern("#");
//								}
//								cellValue = format.format(value);
									NumberFormat nf = NumberFormat.getInstance();
									cellValue=nf.format(value);
									//这种方法对于自动加".0"的数字可直接解决
									//但如果是科学计数法的数字就转换成了带逗号的，例如：12345678912345的科学计数法是1.23457E+13，经过这个格式化后就变成了字符串“12,345,678,912,345”，这也并不是想要的结果，所以要将逗号去掉
									if (cellValue.indexOf(",") >= 0) {
										cellValue= cellValue.replace(",", "");
									}

								}

								break;
							case 1:
								cellValue = cell.getStringCellValue();
								break;
							case 2:
								cellValue = cell.getNumericCellValue() + "";
								// cellValue = String.valueOf(cell.getDateCellValue());
								break;
							case 3:
								cellValue = "";
								break;
							case 4:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case 5:
								cellValue = String.valueOf(cell.getErrorCellValue());
								break;
						}
					} else {
						cellValue = "";
					}

					varmap.put("var"+j, cellValue);

				}
				varList.add(varmap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return varList;
	}

}
