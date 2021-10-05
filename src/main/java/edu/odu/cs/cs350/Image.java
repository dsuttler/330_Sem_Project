package edu.odu.cs.cs350;

/**
 * The Image class is used to hold data pertaining 
 * to an individual image file used on a webpage
 */

public class Image {
    
	private String name;
	private boolean internal;
	// private int numberOfPages;
    // private ArrayList<String> pagesDisplayedOn;
    private int size; 
    
    /**
     * creating an image object without parameters
     */
    public Image()
    {
    	this.name = "";
    	this.internal = false;
    	// this.numberOfPages = 0;
    	// this.pagesDisplayedOn = new ArrayList<String>();
        this.size = 0;
    }

    /**
     * creating an image object with parameters
     * 
     * @param name name of image file
     * @param internal true if internal
     * @param size size of image file
     */
    public Image(String name, boolean internal, int size)
    {
    	this.name = name;
    	this.internal= internal;
    	// this.numberOfPages = numberOfPages;
    	// this.pagesDisplayedOn = pagesDisplayedOn;
        this.size = size;
    }
    
    /**
     * returns name as a string
     * 
     * @return name of image file
     */
    public String getName()
    {
    	return this.name;
    }

    /**
     * sets name from a string variable
     * 
     * @param name name of image file to assign
     */
    public void setName(String name)
    {
    	this.name = name;
    }
    
    /**
     * returns internal as a boolean
     * 
     * @return true if an internal image
     */
    public boolean getInternal()
    {
    	return this.internal;
    }
    
    /**
     * sets internal as a boolean
     * 
     * @param internal sets true/false
     */
    public void setInternal(boolean internal)
    {
    	this.internal = internal;
    }
    
    //returns numberOfPages as an int
    // public int getNumberOfPages()
    // {
    	// return this.numberOfPages;
    // }

    //sets numberOfPages from an int variable
    // public void setNumberOfPages(int numberOfPages)
    // {
    // 	// this.numberOfPages = numberOfPages;
    // }

    //returns pagesDisplayedOn as an arrayList of strings
    // public ArrayList<String> getPagesDisplayedOn()
    // {
    // 	// return this.pagesDisplayedOn;
    // }

    //returns pagesDisplayedOn as an arrayList of strings
    // public void setPagesDisplayedOn(ArrayList<String> pagesDisplayedOn)
    // {
    // 	this.pagesDisplayedOn= pagesDisplayedOn;
    // }

    //adds a single page to the end of the pagesDisplayedOn list
    // public void addPageDisplayedOn (String pageDisplayedOn)
    // {
    // 	this.pagesDisplayedOn.add(pageDisplayedOn);
    // }


    /**
     * returns numberOfImage as an int
     * 
     * @return size of image file
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * sets numberOfImage from an int variable
     * 
     * @param size size of image file to assign
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    /**
     * 
     * @param Image Image object to be copied
     */
    public void copyImage(Image Image) 
    {
    	this.name = Image.getName();
    	this.internal = Image.getInternal();
    	this.size = Image.getSize();
    	// this.numberOfPages = Image.getNumberOfPages();
    	// this.pagesDisplayedOn = Image.getPagesDisplayedOn();
    }
}
