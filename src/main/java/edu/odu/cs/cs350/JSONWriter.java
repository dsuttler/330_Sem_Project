package edu.odu.cs.cs350;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.cedarsoftware.util.io.*;

import java.io.IOException;

/**
 *	The JSONWriter class is used to output to a .json file the data collected from each 
 *	webpage and resource in a website
 */

public class JSONWriter {

	private JsonBuilder objectToWrite;

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Default Constructor
	 *
	 * Creates an empty JsonBuilder object
	 */
	public JSONWriter() {
		objectToWrite = new JsonBuilder();
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Creates a new JsonBuilder object to analyze the src website
	 *
	 * @param src The website to analyze
	 */
	public JSONWriter(Website src) {
		objectToWrite = new JsonBuilder(src);
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Writes objectToWrite to a json file
	 */
	public void WriteToJson() {
		String filename = new SimpleDateFormat("yyyyMMdd-HHmmss'-summary.json'").format(new Date());
		try (FileWriter writer = new FileWriter(filename)){
			String json = JsonWriter.objectToJson(objectToWrite);
			json = JsonWriter.formatJson(json);
			writer.write(json);
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * @return the objectToWrite(JsonBuilder)
	 */
	public JsonBuilder getObjectToWrite() {
		return objectToWrite;
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * @param objectToWrite A new JsonBuilder object to be used for
	 *                      wesite analysis
	 */
	public void setObjectToWrite(JsonBuilder objectToWrite) {
		this.objectToWrite = objectToWrite;
	}

}
