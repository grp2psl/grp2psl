package com.psl.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	/*
	 * GENERALIZED METHOD TO DOWNLOAD EXCEL FILE
	 */
	public void generateExcel(String path, String fileName, String sheetName, List<ExcelFields> fields) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);
		
		
		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);
		headerStyle.setFont(font);
		
		Cell headerCell;
		

		for(int i = 0; i < fields.size(); i++) {
			headerCell = header.createCell(i);
			headerCell.setCellValue(fields.get(i).getFieldName());
			headerCell.setCellStyle(headerStyle);			
		}
				
		Row data = sheet.createRow(1);
		Cell dataCell;
		
		for(int i = 0; i < fields.size(); i++) {
			dataCell = data.createCell(i);
			if(fields.get(i).getFieldType() == XSSFCell.CELL_TYPE_NUMERIC) {
				dataCell.setCellValue(Double.parseDouble(fields.get(i).getFieldValue()));
			}
			else {
				dataCell.setCellValue(fields.get(i).getFieldValue());
			}
			dataCell.setCellType(fields.get(i).getFieldType());			
		}
		
		File file = new File(path, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		System.out.println(file.getPath());
	}

}
