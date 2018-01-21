package com.somero_autocenter;

import org.apache.poi.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;


public class CellWriter {
	
	public static void writeStringToCell(String filePath, int rowNum, int cellNum, String data) {
		
		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			
			Cell cellToModify = sheet.getRow(rowNum).getCell(cellNum);
			cellToModify.setCellValue(data);
			
			
			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
