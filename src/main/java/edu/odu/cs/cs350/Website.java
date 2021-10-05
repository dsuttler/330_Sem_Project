package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 *	The Website class is used to hold data pertaining to a website including
 *	the local path to the website directory and ALL pages/resources within it
 */

public class Website {
    
	private String localPath;
    private ArrayList<String> siteURLs;
    private ArrayList<Html> allPages;
    
    /**
     * creates a Website without variables
     */
    public Website()
    {
    	this.localPath = "";
    	this.siteURLs = new ArrayList<>();
    	this.allPages = new ArrayList<>();
    }
    
    /**
     * creates a Website with variables
     * 
     * @param localPath local path of website directory
     * @param siteURLs ArrayList of URLS 
     * @param allPages ArrayList of webpages
     */
    public Website(String localPath, ArrayList<String> siteURLs, ArrayList<Html> allPages)
    {
    	this.localPath= localPath;
    	this.siteURLs = siteURLs;
    	this.allPages = allPages;
    }

    /**
     * makes a copy of a website
     *
     * @param website Website object to be copied
     */
    public Website(Website website)
    {
        this.localPath= website.getLocalPath();
        this.siteURLs = new ArrayList<>(website.getSiteURLs());
        this.allPages = new ArrayList<>(website.getAllPages());
    }
    
    /**
     * returns the localPath as a string variable
     * 
     * @return local path of website directory
     */
    public String getLocalPath()
    {
    	return this.localPath;
    }
    
    /**
     * sets the local path as a string variable
     * 
     * @param localPath local path of website directory to assign
     */
    public void setLocalPath(String localPath) {
    	this.localPath = localPath;
    }
    
    /**
     * returns the siteURLs as an ArrayList of strings
     * 
     * @return ArrayList of URLs
     */
    public ArrayList<String> getSiteURLs()
    {
    	return this.siteURLs;
    }
    
    /**
     * sets the siteURLs as an ArrayList of strings
     * 
     * @param siteURLs ArrayList of URLs
     */
    public void setSiteURLs(ArrayList<String> siteURLs) 
    {
    	this.siteURLs = siteURLs;
    }
    
    /**
     * adds a siteURL to the end of siteURLs
     * 
     * @param siteURL URL to be added to ArrayList of URLs
     */
    public void addSiteURL(String siteURL)
    {
    	this.siteURLs.add(siteURL);
    }
    
    /**
     * returns the allPages as an ArrayList of Html
     * 
     * @return ArrayList of webpages
     */
    public ArrayList<Html> getAllPages()
    {
    	return this.allPages;
    }
    
    /**
     * sets the allPages as an ArrayList of Html
     * 
     * @param allPages ArrayList of webpages
     */
    public void setAllPages(ArrayList<Html> allPages) 
    {
    	this.allPages = allPages;
    }
    
    /**
     * adds a page to the end of AllPages
     * 
     * @param page page to be added to ArrayList of webpages
     */
    public void addAllPages(Html page)
    {
    	this.allPages.add(page);
    }
    

        
    
}
