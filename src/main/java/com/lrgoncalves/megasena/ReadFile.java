package com.lrgoncalves.megasena;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.Date;

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
			 
			XSSFSheet sheet = workbook.getSheet("Sheet3");
			
			//Get first sheet from the workbook
			//HSSFSheet sheet = workbook.getSheet("Sheet1");					
			//workbook.getSheetAt(0);
			 
			//Get iterator to all the rows in current sheet
			//Iterator<Row> rowIterator = sheet.iterator();
			 
			MongoDBDriver mongoDBDriver = new MongoDBDriver().connect().createCollection();
			
			for (Row row : sheet) {
				//Iterator<Cell> cellIterator = row.cellIterator();	
				
				Result result = new Result(getInt(row.getCell(0))
						,getDate(row.getCell(1))
						,getInt(row.getCell(2))
						,getInt(row.getCell(3))
						,getInt(row.getCell(4))
						,getInt(row.getCell(5))
						,getInt(row.getCell(6))
						,getInt(row.getCell(7)));
				
				if(result.isValidToInsert()){
					System.out.println(result.toJSON());
				}
					//mongoDBDriver.insert(result.toDBObject());
				
				
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
	
	private static Date getDate(Cell cell) throws ParseException{
		if(StringUtils.isEmpty(cell.toString())){
			return null;
		}
		
		System.out.println(cell.toString());
		
		return cell.getDateCellValue();
	}
}
