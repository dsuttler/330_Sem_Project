package edu.odu.cs.cs350;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;




public class WebsiteWalkerTests  {

@Test	
@DisplayName("WebsiteWalker main method test 1 declares paramters and reads in path")


    public static void main(String[] args) {
        String path;    // The path supplied from the command line.
        String pathLC;  // The path transformed to lower case.
    

        //start read in path 
        if (args.length == 0) {
            Scanner stdin = new Scanner(System.in);
            System.out.print("Enter a local path to store copy of URL: ");
            path = stdin.nextLine();
            stdin.close();
            
        }
        else {
            path = args[0];
         
        }
    
        
        pathLC = path.toLowerCase();
        if ( ! (pathLC.startsWith("/home/tkennedy/cs350/sum21/Avocado5") )){
            path = "/home/tkennedy/cs350/sum21/Avocado5" + path;
            System.out.println("Using: " + path);
            /** 
            *this should satisfy "as a website developer I would like to output the location
            *of the results to the CLI"
            */
        }
        System.out.println();
        try {
            readPathFromCLI(path);
        }
        catch (IOException e) {
            System.out.println("\n*** Sorry, an error has occurred ***\n");
            System.out.println(e);
            System.out.println();
        }  
    
         //end reading in path move on to reading urls


  
         String url;    // The url from the command line or from user input.	
         String urlLC;  // The url transformed to lower case.
        
		//start read in urls
		

		
		
        if (args.length == 0) {
            Scanner stdin = new Scanner(System.in);
            System.out.print("Enter a url: ");
            url = stdin.nextLine();
            stdin.close();
        }
        else {
            url = args[0];
        }
        urlLC = url.toLowerCase();
        if ( ! (urlLC.startsWith("http://") || urlLC.startsWith("ftp://") || 
                urlLC.startsWith("file://") || urlLC.startsWith("https://") )) {
            url = "http://" + url;
            System.out.println("Using: " + url);
        }
        System.out.println();
        try {
            readFromURL(url);
        }
        catch (MalformedURLException e) { 
            System.out.println("\n*** Sorry, you entered a malformed URL***\n");
            
        } 
        catch (IOException e) {
            System.out.println("\n*** Sorry, an error has occurred ***\n");
            System.out.println(e);
            System.out.println();
        }  
   
        
       
    } 



	  @Test	
    @DisplayName("<WebsiteWalker> readPathFromCLI method test")

          /*
        * method to read in path from CLI
        */
        static void readPathFromCLI( String pathString ) throws IOException {

            /* read path from CLI, check that path exists and is accessible*/
    
        
            Path path = FileSystems.getDefault().getPath("logs", "access.log");
   //  BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
      

            /* Check that the path belongs to the main directory of 
            /home/tkennedy/cs350/sum21/Avocado5*/
    
           String contentType = ((URLConnection) path).getContentType();
            System.out.println("Stream opened with content type: " + contentType);
            System.out.println();
            if (contentType == null)
                throw new IOException("entered empty path");
            System.out.println();
    
     
    
        } // end readPathFromCLI()


	  
	     @Test	
       @DisplayName("<WebsiteWalker> readFromURL method test")
        /*
        * method to read in url
        */
      static void readFromURL(String urlString ) throws IOException {

        /* Open a connection to the URL, and get an input stream
           for reading data from the URL. */

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        InputStream urlData = connection.getInputStream();


        /* Copy lines of html from the input stream to the screen, until
           end-of-file is encountered  (or an error occurs). */

       BufferedReader in;  // For reading from the connection's input stream.
        in = new BufferedReader( new InputStreamReader(urlData) );

        while (true) {
            String line = in.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
        in.close();

    } // end readFromURL()



} // end class WebsiteWalker


    
