package edu.odu.cs.cs350;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is used to write a website to an excel file
 * 
 */
public class ExcelWriter {
	
	ExcelWriter(){
		
	}
	
	/**
	 * WriteToExcel creates a workbook, sheet
	 * and row. creates a header, then writes
	 * to an excel file.
	 * 
	 * @param website
	 * @throws IOException
	 */
	
    public void WriteToExcel(Website website) throws IOException {
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	XSSFSheet sheet = workbook.createSheet("summary");
    	
    	//setting up the header row
    	int rowCount = 0;
    	Row row = sheet.createRow(rowCount);
    	Cell cell = row.createCell(0);
    	cell.setCellValue((String) "Page");
    	cell = row.createCell(1);
    	cell.setCellValue((String) "#Images");
    	cell = row.createCell(2);
    	cell.setCellValue((String) "#CSS");
    	cell = row.createCell(3);
    	cell.setCellValue((String) "Scripts");
    	cell = row.createCell(4);
    	cell.setCellValue((String) "#Links(Intra-Page)");
    	cell = row.createCell(5);
    	cell.setCellValue((String) "#Links(Internal)");
    	cell = row.createCell(6);
    	cell.setCellValue((String) "#Links(External)");
    	rowCount++;    	    	    	
    	
    	//for loop to grab each of the htmls
    	for (int counter = 0; counter < website.getAllPages().size(); counter++)
    	{
    	row = sheet.createRow(rowCount);
    	this.WriteToExcelHTML(website.getAllPages().get(counter), row);
    	rowCount++;
    	}
    	//sets the filename into the yyyyMMdd format
		String filename = new SimpleDateFormat("yyyyMMdd-HHmmss'-summary.xlsx'").format(new Date());
		//creates a new FileWriter
    	try (FileOutputStream outputStream = new FileOutputStream(filename)){
    		workbook.write(outputStream);
    	}
    	
    	workbook.close();
    }
    
    /**
     * WriteToExcelHTML writes to a row the values
     * of an Html
     * 
     * @param html
     * @param row
     */
    public void WriteToExcelHTML(Html html, Row row)
    {
    	//create cell grabs the path
    	Cell cell = row.createCell(0);
    	cell.setCellValue((String) html.getLocalPath());
    	
    	//grabs the number of images
    	cell = row.createCell(1);
    	cell.setCellValue((int) html.getImages().size());
    	
    	//grabs the number of css
    	cell = row.createCell(2);
    	cell.setCellValue((int) html.getStyleSheets().size());
    	
    	//grabs the number of scripts ****(This maybe asking for something else I am unsure)****
    	cell = row.createCell(3);
    	cell.setCellValue((int) html.getJavascripts().size());
    	
    	//Grabs the links
    	this.SeperateLinks(html, row);
    }
    
    /**
     * grabs the # of intra, internal and external 
     * links
     * 
     * @param html
     * @param row
     */
    public void SeperateLinks(Html html, Row row)
    {
    	int intra= 0;
    	int internal =0;
    	int external =0;
    	for (int counter=0; counter < html.getLinks().size(); counter++)
    	{
    		//if the type is intra
    		if (html.getLinks().get(counter).getClassification() == "Intra-Page"){
    			intra++;
    		}
    		//if the type is internal
    		else if (html.getLinks().get(counter).getClassification() == "Internal"){
    			internal++;
    		}
    		//if the type is external
    		else if (html.getLinks().get(counter).getClassification() == "External"){
    			external++;
    		}	
    	}
    	//create cell intra
    	Cell cell = row.createCell(4);
    	cell.setCellValue((int) intra);
    	
    	//internal
    	cell = row.createCell(5);
    	cell.setCellValue((int) internal);
    	
    	//external
    	cell = row.createCell(6);
    	cell.setCellValue((int) external);
    	
    }
}
