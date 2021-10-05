package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.io.*;

/**
 *	The Html class is used to hold data pertaining 
 *  to an individual webpage file (e.g., .html)
 */

public class Html {
	
    private String localPath;
    private ArrayList<Link> links;
    private ArrayList<Css> styleSheets;
    private ArrayList<Javascript> javaScripts;
    private ArrayList<Image> images;
    private ArrayList<FileResource> fileResources;
    
    /**
     *  creating a new html object without variables
     */
    public Html()
    {
    	this.localPath= "";
    	this.links = new ArrayList<Link>();
    	this.styleSheets = new ArrayList<Css>();
    	this.javaScripts = new ArrayList<Javascript>();
    	this.images = new ArrayList<Image>();
    	this.fileResources = new ArrayList<FileResource>();
    }

    /**
     * creating a html object with variables
     * 
     * @param localPath local path to webpage
     * @param links ArrayList of Link objects
     * @param styleSheets ArrayList of Css objects
     * @param javaScripts ArrayList of Javascript objects
     * @param images ArrayList of Image objects
     * @param fileResources ArrayList of FileResource objects
     */
    public Html(String localPath, ArrayList<Link> links,  ArrayList<Css> styleSheets, ArrayList<Javascript> javaScripts, ArrayList<Image> images, ArrayList<FileResource> fileResources)
    {
    	this.localPath= localPath;
    	this.links = links;
    	this.styleSheets = styleSheets;
    	this.javaScripts = javaScripts;
    	this.images = images;
    	this.fileResources = fileResources;
    }
    
    /**
     *  copies a Html (Copy Constructor)
     *  
     *  @param html object to be copied
     */
    public Html(Html html)
    {
    	this.localPath = html.getLocalPath();
    	this.links = new ArrayList<>(html.links);
    	this.styleSheets = new ArrayList<>(html.styleSheets);
    	this.javaScripts = new ArrayList<>(html.javaScripts);
    	this.images = new ArrayList<>(html.images);
    	this.fileResources = new ArrayList<>(html.fileResources);
    }

    /**
     *  grabs the localPath and returns it as a string
     * 
     *  @return local path to webpage
     */
    public String getLocalPath()
    {
    	return this.localPath;
    }
    
    /**
     *  sets the localPath as a string variable
     *  
     *  @param localPath local path to webpage
     */
    public void setLocalPath(String localPath)
    {
    	this.localPath= localPath;
    }
    
    /**
     * grabs the links and returns it as an arraylist Link
     * 
     * @return ArrayList of link objects
     */
    public ArrayList<Link> getLinks()
    {
    	return this.links;
    }
    
    /**
     * sets the links as an arraylist Link
     * 
     * @param links ArrayList of link objects to assign
     */
    public void setLinks(ArrayList<Link> links)
    {
    	this.links = links;
    }

    /**
     * adds an Link to ArrayList links
     * 
     * @param links
     */
    public void addLink(Link links)
    {
    	this.links.add(links);
    }

    /**
     * grabs the StyleSheets and returns it as an arraylist Css
     * 
     * @return ArrayList of Css objects
     */
    public ArrayList<Css> getStyleSheets()
    {
    	return this.styleSheets;
    }
    
    /**
     * sets the styleSheets as an arraylist Css 
     * 
     * @param styleSheets ArrayList of Css objects to assign
     */
    public void setStyleSheets(ArrayList<Css> styleSheets)
    {
    	this.styleSheets = styleSheets;
    }

    /**
     * adds a styleSheet to the end of styleSheets
     * 
     * @param styleSheet add stylesheet to list of stylsheets
     */
    public void addStyleSheet(Css styleSheet)
    {
    	this.styleSheets.add(styleSheet);
    }
    
    /**
     * grabs the javaScripts and returns it as an arraylist Javascript
     * 
     * @return ArrayList of Javascript objects
     */
    public ArrayList<Javascript> getJavascripts()
    {
    	return this.javaScripts;
    }
    
    /**
     * sets the javaScripts as an arraylist Javascript 
     * 
     * @param javaScripts ArrayList of Javascript objects to assign
     */
    public void setJavaScripts(ArrayList<Javascript> javaScripts)
    {
    	this.javaScripts = javaScripts;
    }
    
    /**
     * adds a Javascript to the end of javaScripts
     * 
     * @param javaScript add script to list of Javascripts
     */
    public void addJavaScript(Javascript javaScript)
    {
    	this.javaScripts.add(javaScript);
    }
    
    /**
     * grabs the images and returns it as an arraylist Images
     * 
     * @return ArrayList of Image objecst
     */
    public ArrayList<Image> getImages()
    {
    	return this.images;
    }
    
    /**
     * sets the images as an arraylist Images 
     * 
     * @param images ArrayList of Image objects to assign
     */
    public void setImages(ArrayList<Image> images)
    {
    	this.images = images;
    }
    
    /**
     * adds a Image to the end of Image
     * 
     * @param image add Image to list of Images
     */
    public void addImages(Image image)
    {
    	this.images.add(image);
    }

    /**
     * grabs the fileResources and returns it as an arraylist FileResource
     * 
     * @return ArrayList of FileResource objects
     */
    public ArrayList<FileResource> getFileResouces()
    {
    	return this.fileResources;
    }
    
    /**
     * sets the fileResources as an arraylist FileResource
     * 
     * @param fileResources ArrayList of FileResource objects to assign
     */
    public void setFileResouces(ArrayList<FileResource> fileResources)
    {
    	this.fileResources = fileResources;
    }
    
    /**
     * adds a FileResource to the end of fileResources
     * 
     * @param fileResource add FileResource to list of FileResources
     */
    public void addFileResource(FileResource fileResource)
    {
    	this.fileResources.add(fileResource);
    }

    /**
     *  Clone - copy--this Html
     *  
     *  @return cloned Html object
     */
    public Html clone()
    {
    	return new Html(this);
    }
    
    /**
     *  Hashcode based on localPath
     *  
     *  @return integer hashcode
     */
    public int hashCode()
    {
    	return this.localPath.hashCode();
    }
    
    /**
     *  toString
     *  
     *  @return Html object as a string
     */
    public String toString()
    {
    	return "path: " + this.localPath;
    }
    
}
