package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
private static Workbook book;
private static Sheet sheet;	
	private static void openExcel(String filepath) {
		try {
			FileInputStream fis= new FileInputStream(filepath);
			 book= new XSSFWorkbook(fis); //will read the data in xlsx format
		//	Workbook book1= new HSSFWorkbook(fis);//will read the data in xls format
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
						e.printStackTrace();
		}
	}
	
	private static void loadSheet(String sheetName) {
		sheet=  book.getSheet(sheetName);
	}
	
	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
   private static int columnCount(int rowindex) {
	return sheet.getRow(rowindex).getLastCellNum();	
	}
	
   private static String cellData(int rowIndex,int columnIndex) {
	   return sheet.getRow(rowIndex).getCell(columnIndex).toString();
   }

    public static Object[][] excelIntoArray(String filepath,String sheetName){
    	openExcel(filepath);
    	loadSheet(sheetName);
		int rows=rowCount();
		int columns=columnCount(0);
    	Object[][] data= new Object[rows-1][columns];
    	
    	for(int i=1;i<rows;i++) {
    		for(int j=0;j<columns;j++) {
    			data[i-1][j]=cellData(i,j);
    		}
    	}
    	
    	return data;
    	
    }




}
