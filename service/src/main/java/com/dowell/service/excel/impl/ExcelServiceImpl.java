package com.dowell.service.excel.impl;

import com.dowell.common.result.ResponseBo;
import com.dowell.service.excel.ExcelService;
import com.dowell.util.ExcelUtils;
import com.dowell.util.FileUtils;
import com.dowell.util.PutExcelToData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description
 * @create 2018-10-03
 **/
@Service
public class ExcelServiceImpl implements ExcelService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${export.excel.tempDir}")
	private String exportTempDir;

	@Override
	public ResponseBo createExcelByPoiKit(String filename, List<?> list, Class<?> clazz) {
		if (list.isEmpty()) {
			return ResponseBo.warn("导出数据为空！");
		} else {
			boolean operateSign = false;

			String excelFolderPath = exportTempDir;
			if (!excelFolderPath.endsWith("/")){
				excelFolderPath += File.separator;
			}
			String fileName = filename + ".xlsx";
			fileName = FileUtils.makeFileName(fileName);
			try {
				File fileDir = new File(excelFolderPath);
				if (!fileDir.exists()) {
					fileDir.mkdir();
				}
				String path = excelFolderPath + fileName;
				FileOutputStream fos;
				fos = new FileOutputStream(path);
				operateSign = ExcelUtils.builder(clazz)
						// 设置每个sheet的最大记录数,默认为10000,可不设置
						 .setMaxSheetRecords(10000)
						.toExcel(list, "查询结果", fos);
			} catch (FileNotFoundException e) {
				log.error("文件未找到", e);
			}
			if (operateSign) {
				return ResponseBo.ok(fileName);
			} else {
				return ResponseBo.error("导出Excel失败，请联系网站管理员！");
			}
		}
	}

	@Override
	public ResponseBo createCsv(String filename, List<?> list, Class<?> clazz) {
		if (list.isEmpty()) {
			return ResponseBo.warn("导出数据为空！");
		} else {
			boolean operateSign;

			String excelFolderPath = exportTempDir;
			if (!excelFolderPath.endsWith("/")){
				excelFolderPath += File.separator;
			}

			String fileName = filename + ".csv";
			fileName = FileUtils.makeFileName(fileName);

			File fileDir = new File(excelFolderPath);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			String path = excelFolderPath + fileName;
			operateSign = ExcelUtils.builder(clazz)
					.toCsv(list, path);
			if (operateSign) {
				return ResponseBo.ok(fileName);
			} else {
				return ResponseBo.error("导出Csv失败，请联系网站管理员！");
			}
		}
	}

	@Override
	public List<Map<String, Object>> readExcelData(InputStream fis, String filePath, String filename) {
		if (filename.endsWith(".xls")){
			return PutExcelToData.putExcel(fis, filePath, filename);
		}else {
			return PutExcelToData.readExcelForXlsx(fis, filePath, filename);
		}
	}

	@Override
	public List<Map<String, List<String>>> readExcelLogisticsCodeData(InputStream fis, String filename) {

		try {
			List<Map<String,List<String>>> temp = new ArrayList();
			Map<String,List<String>> map1 = new HashMap();

			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			for (int i = 1; i < rownum+1; i++) {
				String key ="";
				List<String> value = new ArrayList<>();
				int colnum = sheet.getRow(1).getPhysicalNumberOfCells();
				b:  for (int j = 0; j < colnum; j++) {
					Row row = sheet.getRow(i);
					Cell cell = row.getCell(j);
					if(StringUtils.isEmpty(getCellValue(cell))){
						map1.put(key,value);
						break b;
					}
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(j==0){
						key = getCellValue(cell);
					}
					else{
						value.add(getCellValue(cell));
						if(j==colnum-1){
							map1.put(key,value);
						}
					}
				}
			}
			temp.add(map1);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String getCellValue(Cell c) {
		String o = null;
		switch (c.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				o = ""; break;
			case Cell.CELL_TYPE_BOOLEAN:
				o = String.valueOf(c.getBooleanCellValue()); break;
			case Cell.CELL_TYPE_FORMULA:
				o = String.valueOf(c.getCellFormula()); break;
			case Cell.CELL_TYPE_NUMERIC:
				o = String.valueOf(c.getNumericCellValue()); break;
			case Cell.CELL_TYPE_STRING:
				o = c.getStringCellValue(); break;
			default:
				o = null;
				break;
		}
		return o;
	}

}
