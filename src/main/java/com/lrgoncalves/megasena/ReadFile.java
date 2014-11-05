package com.lrgoncalves.megasena;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadFile {

	public static void main(String[] args) {
		
		
		try{
			
			FileInputStream file = new FileInputStream(new File("C:\\workspace\\sources\\mega-sena-results.xlsx"));
			//Get the workbook instance for XLS file 
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//HSSFWorkbook workbook = new HSSFWorkbook(file);
			 
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			
			//Get first sheet from the workbook
			//HSSFSheet sheet = workbook.getSheet("Sheet1");					//workbook.getSheetAt(0);
			 
			//Get iterator to all the rows in current sheet
			//Iterator<Row> rowIterator = sheet.iterator();
			 
			MongoDBDriver mongoDBDriver = new MongoDBDriver().connect().createCollection();
			
			for (Row row : sheet) {
				//Iterator<Cell> cellIterator = row.cellIterator();	
				
				Result result = new Result(getInt(row.getCell(0))
						,getInt(row.getCell(1))
						,getInt(row.getCell(2))
						,getInt(row.getCell(3))
						,getInt(row.getCell(4))
						,getInt(row.getCell(5)));
				
				if(result.isValidToInsert())
					mongoDBDriver.insert(result.toJSON());
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	

	}

	private static int getInt(Cell cell){
		if(StringUtils.isEmpty(cell.toString())){
			return 0;
		}
		return new Double(cell.toString()).intValue();
	}
}
