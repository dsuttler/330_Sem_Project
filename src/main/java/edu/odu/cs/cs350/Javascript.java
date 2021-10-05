package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 *	The Javascript class is used to hold data pertaining 
 *  to an individual JavaScript file (e.g., .js)
 */

public class Javascript {
    private String name;
    private String classification; 
    private ArrayList<String> pagesDisplayedOn;
   
    /**
     *  Create uninitialized Javascript object
     */
    public Javascript()
    {
    	this.name = "";
    	this.classification = "";
    	this.pagesDisplayedOn = new ArrayList<>();
    }
    
    /**
     *  Create initialized Javascript object
     * @param name URL of script
     * @param classification internal vs external
     * @param pagesDisplayedOn ArrayList of webpages script file is used on
     */
    public Javascript(String name, String classification, ArrayList<String> pagesDisplayedOn)
    {
    	this.name = name;
    	this.classification = classification;
    	this.pagesDisplayedOn = pagesDisplayedOn;
    }
    
    /**
     *  Copy Constructor
     *  
     *  @param src Javascript object to be copied
     */
    public Javascript(Javascript src)
    {
    	this.name = src.name;
    	this.classification = src.classification;
    	this.pagesDisplayedOn = new ArrayList<>(src.pagesDisplayedOn);
    }
    
    /**
     *  Retrieve Javascript URL name
     *  
     *  @return URL of script file
     */
    public String getName()
    {
    	return this.name;
    }
    
    /**
     *  Set Javascript URL name
     * 
     *  @param n URL of script file to assign
     */
    public void setName(String n)
    {
    	this.name = n;
    }
    
    /**
     *  Set Javascript classification
     *  
     *  @return internal or external
     */
    public String getClassification()
    {
    	return this.classification;
    }
    
    /**
     *  Retrieve Javascript classification
     *  
     *  @param c internal or external
     */
    public void setClassification(String c)
    {
    	this.classification = c;
    }
    
    /**
     *  Retrieve list of strings containing names of pages a particular javascript file is used on
     *  
     *  @return ArrayList of webpages the script file is used on
     */
    public ArrayList<String> getPages()
    {
    	return this.pagesDisplayedOn;
    }
    
    /**
     *  Set list of pages javascript file is used on
     *  
     *  @param p ArrayList of webpages to be assigned
     */
    public void setPages(ArrayList<String> p) 
    {
    	this.pagesDisplayedOn = p;
    }
    
    /**
     *  Add page to list
     *  
     *  @param p HTML URL to be added to ArrayList
     */
    public void addPage(String p)
    {
    	this.pagesDisplayedOn.add(p);
    }
    
    /**
     *  Check for equivalence based on name
     *  
     *  @param rhs object against which to compare equality
     *  
     *  @return true if two Javascript objects are equal
     */
    public boolean equals(Javascript rhs)
    {
        return this.name.equals(rhs.getName());
    }
    
    /**
     *  Hashcode ( < )
     *  
     *  @return integer hashcode
     */
    public int hashCode()
    {
    	return this.name.hashCode();
    }
    
    /**
     *  Clone
     *  
     *  @return cloned Javascript object
     */
    public Javascript clone()
    {
    	return new Javascript(this);
    }
    
    /**
     *  toString function
     *  
     *   @return Javascript object as string
     */
    public String toString()
    {
    	return ("scriptPaths: [" + name.toString() + "]");
    }
    
}



