package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextFileWriterTest {

    /**
     *  Objects used in the tests 
     */
	Image nonDefaultImage;
    Website nonDefaultWebsite;
    Html nonDefaultHtml;
    
    @BeforeEach
    void setup() {    	
    	
    	nonDefaultWebsite = new Website();
    	nonDefaultHtml = new Html();
    	nonDefaultImage= new Image();
    	int stringcounter=0;
    	for (int counter = 0; counter < 3; counter++) 
    	{
    	nonDefaultHtml = new Html();
    	nonDefaultHtml.setLocalPath("HtmlLocalPath " + counter);
    	for (int counter2 = 0; counter2 < 10; counter2++)
    	{
    	nonDefaultImage = new Image();
    	nonDefaultImage.setName("ImageName " + stringcounter);
    	nonDefaultImage.setInternal(true);
    	nonDefaultImage.setSize(stringcounter * 5);
    	stringcounter++;
    	nonDefaultHtml.addImages(nonDefaultImage);
    	}
    	nonDefaultWebsite.addAllPages(nonDefaultHtml);
    	nonDefaultWebsite.setLocalPath("WebsiteLocalPath Main");
    	
    	}
    }
    	
	@Test
	@DisplayName("<TextFile> output")
	public void TextFileOutputTest()
	{
		try
		{
			FileWriter writer = new FileWriter("TestTextFile.txt"); 
			writer.write("Hello World\n");
			writer.write("This is a test for FileWriter");
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("<TextFile> output")
	public void TextFileForLoopTest()
	{
		try
		{
			FileWriter writer = new FileWriter("TestTextFileForLoop.txt"); 
			for (int counter = 0; counter < 10; counter++) {
				writer.write("Hello World" + counter + "\n");
			}
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	@DisplayName("<TextFile> Html")	
	public void TextFileWriterHtml()
	{
		try
		{
			FileWriter writer = new FileWriter("TestTextFileHTML.txt"); 
			TextFileWriter textwriter = new TextFileWriter();
			textwriter.TextFileWriterHTML(nonDefaultWebsite.getAllPages().get(0), writer);
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
		@Test
		@DisplayName("<TextFile> Website")	
		public void TextFileWriterWebsite()
		{
				TextFileWriter textwriter = new TextFileWriter();
				textwriter.TextFileWriterWebsite(nonDefaultWebsite);
		}
}
