package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    private ArrayList<String> htmlToBeProcessed;
    Parser(){
    this.htmlToBeProcessed=new ArrayList<String>();	
    }
    /**
    *	WebsiteParser is going through the website
    * html and gathers them all
    *
    *	@param String, String[]
     * @throws IOException 
    */
    Website parseWebsite(String localDir, String[] HtmlDocs) throws IOException {
    	Website newWebsite= new Website();
    	newWebsite.setLocalPath(localDir);
    	for (int counter=0; counter < HtmlDocs.length; counter++) {
    		this.htmlToBeProcessed.add(localDir + HtmlDocs[counter]);
    	}
    	int counter=0;
    	while(counter < this.htmlToBeProcessed.size()){
    		newWebsite.addAllPages(this.parseHtml(this.htmlToBeProcessed.get(counter), localDir));
    		for (int counter2=0; counter2 < newWebsite.getAllPages().get(counter).getLinks().size();counter2++){
    			if (newWebsite.getAllPages().get(counter).getLinks().get(counter2).getClassification().equals("Internal")){
    			if (this.SearchArrayListStrings(localDir + newWebsite.getAllPages().get(counter).getLinks().get(counter2).getName(), this.htmlToBeProcessed)==false)
    			{
    				this.htmlToBeProcessed.add(localDir + newWebsite.getAllPages().get(counter).getLinks().get(counter2).getName());
    			}
    			}    			
    		}
    		counter++;
    		
    	}
    	newWebsite.setLocalPath(localDir);
    	
    	for (int counter1=0; counter1< newWebsite.getAllPages().size()-1; counter1++)
    	{
    		for (int counter2=counter1+1; counter2<newWebsite.getAllPages().size(); counter2++)
    		{
    			if(newWebsite.getAllPages().get(counter1).getLocalPath().compareTo(newWebsite.getAllPages().get(counter2).getLocalPath())> 0) {
    			Html temp = new Html(newWebsite.getAllPages().get(counter1));
    			newWebsite.getAllPages().set(counter1,newWebsite.getAllPages().get(counter2));
    			newWebsite.getAllPages().set(counter2, temp);
    			}
    		}
    	}
    	return newWebsite;
    }
    /**
     * This function will grab the elements on an
     * HTML Document and sort the information.
     * It then goes through a for loop to add the 
     * elements to a new HTML.
     * 
     * @param String, String
     * @throws IOException 
     */
    Html parseHtml(String absoluteHtmlFile, String localDir) throws IOException
    {
    	File input = new File(absoluteHtmlFile);
    	Document doc= Jsoup.parse(input,"UTF-8");
    	Html newHtml= new Html();
    	Elements links = doc.select("a");
    	Elements css = doc.select("link");
    	Elements javascripts= doc.select("script");
     	Elements images = doc.select("img");
    	Elements fileResources = doc.select("source");
    	newHtml.setLocalPath(absoluteHtmlFile.substring(localDir.length()));
  /*
   * These will all be the loops for the HTML
   */ 
    	for (int counter=0; counter < links.size(); counter++) {
    		if (links.get(counter).attr("href") != "") {
    			newHtml.addLink(this.parseLink(links.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));
    		}
    	}
    
      	for (int counter = 0; counter < css.size(); counter++)
      	{
      		if (css.get(counter).attr("href") != "") {
      			if (css.get(counter).attr("rel").equals("stylesheet")) {
      				newHtml.addStyleSheet(parseCss(css.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));	
      			}
      		}
    	} 	
      	
      	for (int counter = 0; counter < javascripts.size(); counter++)
    	{
      		if (javascripts.get(counter).attr("src")!="") {
    		newHtml.addJavaScript(parseJavascript(javascripts.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));
      		}
    	}
    	
      	for (int counter = 0; counter < fileResources.size(); counter++)
    	{
      		if (fileResources.get(counter).attr("src")!="") {
      		FileResource tempFileResource= new FileResource();
      		tempFileResource.CopyFileResource(parseFileResource(fileResources.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));
      		if (tempFileResource != null) {
    		newHtml.addFileResource(parseFileResource(fileResources.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));
      		}
    		}
    	}
    	
    	for (int counter = 0; counter < images.size(); counter++)
    	{
    		if (images.get(counter).attr("src")!="") {
    		newHtml.addImages(parseImage(images.get(counter), localDir, absoluteHtmlFile.substring(localDir.length())));
    		}
    	}
    	
    	return newHtml;
    }

    /**
     * When given an element, it will search the element
     * for link attributes and return them in an object 
     * link
     * 
     * @param Elements, String, String
     */
    Link parseLink(Element link, String localDir, String absoluteHtmlFile)
    {
    	Link newLink= new Link();
    	String absoluteHref = this.ReturnAbsolutePath(link.attr("href"), localDir, absoluteHtmlFile);
    	newLink.setName(absoluteHref);
    	if (absoluteHref.length()>localDir.length()) {
    	if (absoluteHref.substring(0,localDir.length()).equals(localDir))
    	{
    			if (absoluteHref.substring(localDir.length()).equals(absoluteHtmlFile)){
    			newLink.setName(absoluteHref.substring(localDir.length()));
    			newLink.setClassification("Intra-Page");    			
    			return newLink;
    		}
    		else
    		{
    			newLink.setName(absoluteHref.substring(localDir.length()));
    			newLink.setClassification("Internal");
    			return newLink;
    		}
    		}
    		//pushing into array
    	}
    	
    	newLink.setName(absoluteHref);
    	newLink.setClassification("External");
    	return newLink;
    	
    }

    /**
     * When given an element it will create a new css
     * and return the attributes as a Css object
     * 
     * @param Element, String, String
     */
    Css parseCss(Element css, String localDir, String htmlDocPath)
    {
    	Css newCss= new Css();
    	String absoluteSrc = this.ReturnAbsolutePath(css.attr("href"), localDir, htmlDocPath);
    	if (absoluteSrc.length()> localDir.length()) {
    	if (absoluteSrc.substring(0,localDir.length()).equals(localDir)) {
    		newCss.setName(absoluteSrc.substring(localDir.length()));
    		newCss.setClassification("Internal");
    		return newCss;
    	}
    	}
    		newCss.setName(absoluteSrc);
    		newCss.setClassification("External");
    	
    	return newCss;
    }
    /**
     * When given an element, it will search the element
     * for Javascript attributes then return the attributes
     * as a Javascript object
     * 
     * @param Element, String
     */
    Javascript parseJavascript(Element javascript, String localDir, String htmlDocPath)
    {
    	Javascript newJavascript= new Javascript();
    	
    	String absoluteSrc = this.ReturnAbsolutePath(javascript.attr("src"), localDir, htmlDocPath);
    	
    	if (absoluteSrc.length() > localDir.length()) {
    	if (absoluteSrc.substring(0,localDir.length()).equals(localDir))
    	{
    		newJavascript.setName(absoluteSrc.substring(localDir.length()));
    		newJavascript.setClassification("Internal");
    		return newJavascript;
    	}
    	}
    	
    		newJavascript.setName(absoluteSrc);
    		newJavascript.setClassification("External");
    	
    	return newJavascript;
    }
    /**
     * When given an element, it will search the element
     * and return an Image object from the element 
     *       
     * @param Element, String
     */
    Image parseImage(Element image, String localDir, String htmlDocPath)
	{
    	Image newImage=new Image();
    	String absoluteSrc = this.ReturnAbsolutePath(image.attr("src"), localDir, htmlDocPath);
    	//internal image
    	if (absoluteSrc.length()> localDir.length()) {
    	if (absoluteSrc.substring(0,localDir.length()).equals(localDir))
    		{
    		newImage.setName((absoluteSrc.substring(localDir.length())));
    		newImage.setInternal(true);
    		File input =  new File(absoluteSrc);
    		newImage.setSize((int)(input.length() / 1024));
    		return newImage;
    		}
    	//external image
    	}
    		newImage.setName(absoluteSrc);
    		newImage.setInternal(false);

    	return newImage;
	}
    /**
     * when given an element, it will search the element
     * and return a FileResource object from the element
     * 
     * @param Element, String
     */
    FileResource parseFileResource(Element fileResource, String localDir, String htmlDocPath)
    {
    	FileResource newFileResource= new FileResource();
    	String absoluteSrc = this.ReturnAbsolutePath(fileResource.attr("src"), localDir, htmlDocPath);
    	newFileResource.setLocalPath(absoluteSrc);
    	if (absoluteSrc.length()>localDir.length())
    	{
        	if (absoluteSrc.substring(0,localDir.length()).equals(localDir)) {
    		newFileResource.setLocalPath(absoluteSrc.substring(localDir.length()));
    		newFileResource.setCategory(fileResource.attr("type"));
    		File input =  new File(absoluteSrc);
    		newFileResource.setSize((int)(input.length()/ 1024));    		
    		return newFileResource;
        	}
    	}
    	return null;
    }
    
    /**
     * This will sort through an arraylist of strings 
     * and return whether the string is in the list or
     * not as a boolean.
     * 
     * @param String,  ArrayList<String>
     */
    boolean SearchArrayListStrings(String html, ArrayList<String> htmlToBeProcessed)
    {
    	boolean onList = false;
    	for (int counter = 0; counter< htmlToBeProcessed.size(); counter++)
    	{
    	if (html.equals(htmlToBeProcessed.get(counter)))
    	{
    		onList=true;
    	}
    	}
    	return onList;
    }
    
    /**returns an AbsolutePath as a string, if it is external it will
     * only return attr.
     * 
     * @param String, String, String
    */
    String ReturnAbsolutePath(String attr, String localDir, String html)
    {
    	
    	String copyOfAttr= new String();
    	String copyOfHtml = new String();
    	String absolutePath= new String();
    	boolean noIssues= false;
    	boolean tooFar=false;
    	copyOfAttr = attr;
    	copyOfHtml = html;
    	copyOfHtml = this.RemovePath(copyOfHtml);
    	while ((noIssues == false) && (tooFar!= true))
    	{
    	noIssues=true;
    		if (copyOfAttr.substring(0,2).equals("../"))
    		{
    			if (copyOfHtml.length()==0) {
    				tooFar=true;
    				copyOfHtml=localDir;
    				noIssues=false;
    			}
    			copyOfAttr= copyOfAttr.substring(3);
    			copyOfHtml= this.RemovePath(copyOfHtml);
    			noIssues=false;
    		}
    		if (copyOfAttr.substring(0, 1).equals("./"))
    		{
    			copyOfAttr= copyOfAttr.substring(2);
    			noIssues=false;
    		}
    		
    	}
    	if (tooFar==true)
    	{
    		absolutePath = copyOfAttr;
    	}
    	else {
    		absolutePath = localDir + copyOfHtml + "/" + copyOfAttr;
    	}
    	String copyAbsolutePath= absolutePath.replaceAll("%20", " ");
    	File tempFile= new File(copyAbsolutePath.replaceAll("%20", " "));
    	
    	if (tempFile.exists()==true)
    	{
        		return absolutePath;
    	}
    	else
    	{
    		return attr;
    	}
    }
    
    
    /**
     * This is used to remove a path from a link.
     * 
     * @param String
     */
    String RemovePath(String html) {
    	String copyOfAbsoluteDir= new String();
    	copyOfAbsoluteDir=html;
    	boolean found=false;
    	int counter = html.length();
    	while ((found != true) && (counter!=0))
    	{
    		if (html.substring(counter-1,counter).equals("/"))
    		{
    		found = true;	
    		}
    		
    		counter =counter- 1;
    	}    	
    	if (counter==0)
    	{
    		return "";
    	}
    	
    	return copyOfAbsoluteDir.substring(0, counter);
    	
    }

}