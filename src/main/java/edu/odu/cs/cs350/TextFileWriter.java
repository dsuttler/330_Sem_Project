package edu.odu.cs.cs350;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *	The TextFileWriter class is used to output to a .txt file the local page sizes
 *	and local paths from all local pages of a website.
 */
public class TextFileWriter {

	public TextFileWriter()
	{
	}
	
	/**
	 * writes on the website level for txt file
	 * 
	 * @param website collection of webpages
	 */
	public void TextFileWriterWebsite(Website website)
	{
		//try 
		try {
			int TotalMemoryInWebsite =0;
			//sets the filename to the yyyyMMdd format
			String filename = new SimpleDateFormat("yyyyMMdd-HHmmss'-summary.txt'").format(new Date());
			//creates a new FileWriter
			FileWriter writer = new FileWriter(filename);
			writer.write("size\t\tpage\n");
			//for loop to grab each html pages memory
			for (int counter = 0; counter < website.getAllPages().size(); counter++){
					TotalMemoryInWebsite = TotalMemoryInWebsite + this.TextFileWriterHTML(website.getAllPages().get(counter), writer);
			}
			//writes the total memory on the website
			
			writer.write("" + TotalMemoryInWebsite + "");
			writer.close();
		}
		// catch
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ******THIS RIGHT NOW DOESN'T ACCOUNT FOR M or K on*******
	 * 
	 * @param html a webpage
	 * @param writer filewriter stream
	 * 
	 * @return amount of data contained on webpage
	 *
	 * writes on the HTML level
	 */
	public int TextFileWriterHTML(Html html, FileWriter writer)
	{
		int MemoryInPage = 0;
		//loop to get the size of each image in an html object.
		for (int counter = 0; counter < html.getImages().size(); counter++) {
			//grabs the size of each image
			MemoryInPage = MemoryInPage + html.getImages().get(counter).getSize();
		}
		//try 
		try {
		//writer 
		writer.write(MemoryInPage + "\t\t" + html.getLocalPath()+ "\n");
		}	
		// catch exception
		catch (IOException e) {
			e.printStackTrace();
		}
		//returning the amount of memory in one page (total from all images)
		return MemoryInPage;
	}
	
}
