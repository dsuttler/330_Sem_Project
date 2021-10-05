package edu.odu.cs.cs350;

/**
 *	The Link class is used to hold data pertaining 
 * 	to an individual link appearing on a webpage
 */

public class Link {
	
	private String name;
	private String classification;

	/**
	 * Default Constructor
	 */
	public Link(){
		this.name = "";
		this.classification = "";
	}
	
	/**
	 * Nondefault Constructor
	 * 
	 * @param name link URL
	 * @param classification internal vs external
	 */
	public Link(String name, String classification)
	{
		this.name = name;
		this.classification = classification;
	}
	
	/**
	 * returns the name of a link as a string
	 * 
	 * @return URL of link
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * sets the name of a link as a string
	 * 
	 * @param name URL of link
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * returns the type of a link as a string
	 * 
	 * @return internal vs external
	 */
	public String getClassification()
	{
		return this.classification;
	}
	
	/**
	 * sets the type of a link as a string
	 * 
	 * @param classification internal vs external
	 */
	public void setClassification(String classification)
	{
		this.classification = classification;
	}
	
}
