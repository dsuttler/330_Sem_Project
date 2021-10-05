package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelWriterTests {


	XSSFWorkbook nonDefaultWorkbook;
    Website nonDefaultWebsite;
    Html nonDefaultHtml;
    Row nonDefaultRow;

/**
* Sets up nonDefaultRow, nonDefaultWebsite, and nonDefaultHtml
*/
  @BeforeEach
   void setup() { 

	  	nonDefaultWorkbook = new XSSFWorkbook();
    	XSSFSheet sheet = nonDefaultWorkbook.createSheet("Tests");
    	
    	//setting up the header row 
    	nonDefaultRow = sheet.createRow(0);
    	Cell cell = nonDefaultRow.createCell(0);
    	cell.setCellValue((String) "Page");
    	cell = nonDefaultRow.createCell(1);
    	cell.setCellValue((String) "#Images");
    	cell = nonDefaultRow.createCell(2);
    	cell.setCellValue((String) "#CSS");
    	cell = nonDefaultRow.createCell(3);
    	cell.setCellValue((String) "Scripts");
    	cell = nonDefaultRow.createCell(4);
    	cell.setCellValue((String) "#Links(Intra-Page)");
    	cell = nonDefaultRow.createCell(5);
    	cell.setCellValue((String) "#Links(Internal)");
    	cell = nonDefaultRow.createCell(6);
    	cell.setCellValue((String) "#Links(External)");
    	//setting up the nonDefaultRow to be the next line
    	nonDefaultRow = sheet.createRow(1);
    	
    	
    	Image testImage= new Image();
    	Css testCss = new Css();
    	Javascript testScripts = new Javascript();
    	nonDefaultWebsite= new Website();
    	nonDefaultHtml = new Html();
    	for (int counter=0; counter<3; counter++) 
    	{
    	nonDefaultHtml = new Html();
    	nonDefaultHtml.setLocalPath("HtmlLocalPath " + counter);
    	for (int counter2=0; counter2<10; counter2++)
    	{
    	testImage= new Image();
    	nonDefaultHtml.addImages(testImage);
    	testCss= new Css();
    	nonDefaultHtml.addStyleSheet(testCss);
    	testScripts = new Javascript();
    	nonDefaultHtml.addJavaScript(testScripts);
    	}
    	for (int counter3=0; counter3<3; counter3++)
    	{
    	Link testLink1= new Link();
        testLink1.setClassification("Intra-Page");
        nonDefaultHtml.addLink(testLink1);
        Link testLink2= new Link();
        testLink2.setClassification("Internal");
        nonDefaultHtml.addLink(testLink2);
        Link testLink3= new Link();
        testLink3.setClassification("External");
    	nonDefaultHtml.addLink(testLink3);
    	}
    	nonDefaultWebsite.addAllPages(nonDefaultHtml);
    	}
    	nonDefaultWebsite.setLocalPath("Website Local Path");
    	
    	
    }


  
   /**
	* Test for looping through htmls
	* @throws IOException 
	*/
 	@Test
    @DisplayName("<ExcelWriter> WriteToExcelTest")
 	public void WriteToExcelTest() throws IOException
 	{
 		ExcelWriter xlsxWriter= new ExcelWriter();
 		xlsxWriter.WriteToExcel(nonDefaultWebsite);
 	}

   /**
	* Test for writing html to excel 
	* @throws IOException 
	*/
    @Test
    @DisplayName("<ExcelWriter> WriteToExcelHtmlTest")
    
    public void WriteToExcelHtmlTest() throws IOException
    {
    	ExcelWriter xlsxWriter= new ExcelWriter();
    	xlsxWriter.WriteToExcelHTML(nonDefaultHtml, nonDefaultRow);
		String filename = "ExcelHtmlTest.xlsx";
		//creates a new FileWriter
    	try (FileOutputStream outputStream = new FileOutputStream(filename)){
    		nonDefaultWorkbook.write(outputStream);
    	}
    	
    	nonDefaultWorkbook.close();

    }
    
   /**
	* Test for bringing in # of intra, internal and external links from html
 * @throws IOException 
	*/
    @Test
    @DisplayName("<ExcelWriter> SeperateLinksTest")

     public void SeperateLinksTest() throws IOException
    {
    	ExcelWriter xlsxWriter= new ExcelWriter();
    	xlsxWriter.SeperateLinks(nonDefaultHtml, nonDefaultRow);
    
		String filename = "SeperateLinksTest.xlsx";
		//creates a new FileWriter
    	try (FileOutputStream outputStream = new FileOutputStream(filename)){
    		nonDefaultWorkbook.write(outputStream);
    	}
    	
    	nonDefaultWorkbook.close();
    	
    }



}

