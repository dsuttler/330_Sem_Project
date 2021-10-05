package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 *  The Css class is used to hold data pertaining 
 *  to an individual stylesheet.
 */

public class Css {
    private String name;
    private String classification; // internal vs external
    private ArrayList<String> pagesDisplayedOn; 
   
    /*
     *  Create uninitialized Css object
     */
    public Css()
    {
    	this.name = "";
    	this.classification = "";
    	this.pagesDisplayedOn = new ArrayList<String>();
    }
    
    /**
     *  Create initialized Css object
     *  
     *  @param name URL of stylesheet
     *  @param classification internal vs external
     *  @param pagesDisplayedOn pages that stylesheet is used on
     */
    public Css(String name, String classification, ArrayList<String> pagesDisplayedOn)
    {
    	this.name = name;
    	this.classification = classification;
    	this.pagesDisplayedOn = pagesDisplayedOn;
    }
    
    /**
     *  Copy Constructor
     *  
     *  @param src Css object to be copied
     */
    public Css(Css src)
    {
    	this.name = src.name;
    	this.classification = src.classification;
    	this.pagesDisplayedOn = new ArrayList<String>(src.pagesDisplayedOn);
    }
    
    /**
     *  Retrieve Css URL name
     *  
     *  @return URL of style sheet
     */
    public String getName()
    {
    	return this.name;
    }
    
    /**
     *  Set Css URL name
     *  
     *  @param URL to assign
     */
    public void setName(String n)
    {
    	this.name = n;
    }
    
    /**
     *  Retrieve Css classification
     *  
     * @return external vs internal
     */
    public String getClassification()
    {
    	return this.classification;
    }
    
    /**
     *  Set Css classification
     *  
     * @param c external vs internal
     */
    public void setClassification(String c)
    {
    	this.classification = c;
    }
    
    /**
     *  Retrieve list of strings containing names of pages that a particular stylesheet is used on
     *  
     * @return ArrayList of HTML URLs
     */
    public ArrayList<String> getPages()
    {
    	return this.pagesDisplayedOn;
    }
    
    /**
     *  Set list of pages stylesheet is used on
     *  
     * @param p ArrayList of HTML URls to asign
     */
    public void setPages(ArrayList<String> p) 
    {
    	this.pagesDisplayedOn = p;
    }
    
    /**
     *  Add page to list
     *  
     * @param page HTML URL to be added to ArrayList
     */
    public void addPage(String page)
    {
    	this.pagesDisplayedOn.add(page);
    }
    
    /**
     *  Check for equivalence based on name
     *  
     *  @param rhs object against which to compare equality
     *  
     *  @return true if two Css objects are equal
     */
    public boolean equals(Object rhs)
    {
    	Css c = (Css) rhs;
    	
    	return this.name.equals(c.name);
    }
    
    /**
     *  Hashcode
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
     *  @return cloned Css object
     */
    public Css clone() 
    {
    	return new Css(this);
    }
    
    /**
     *  toString function 
     *  
     *  @return Css object as a string
     */
    public String toString()
    {
    	return ("cssPaths: [" + name.toString() + "]");
    }
    
}
