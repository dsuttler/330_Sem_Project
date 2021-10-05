package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 * JsonImage will extract the required information from an Image for the
 * JsonBuilder class
 */
public class JsonImage {
    private String fileName; // File name if the image
    private int numPagesDisplayed; // Number of pages this image is displayed on
    private ArrayList<String> pageList; // A list of the filenames of the HTML pages this image is displayed on

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Default Constructor
     *
     * Sets the fileName to 'untitled'
     * Sets the numPagesDisplayed on to 0
     * Sets the pageList to an empty ArrayList
     */
    public JsonImage() {
        this.fileName = "untitled";
        this.numPagesDisplayed = 0;
        this.pageList = new ArrayList<>();
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Non-Default Constructor
     *
     * @param newName Sets the fileName of this image
     * @param src A list of Html object to search
     */
    public JsonImage(String newName, ArrayList<Html> src) {
        // Setting all defaults to begin with
        this.fileName = newName;
        this.numPagesDisplayed = 0;
        this.pageList = new ArrayList<>();
        imageSearch(src);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Looping through an ArrayList of HTML pages
     * for each page we look in its corresponding list of Images
     * for each Image we check if the filename matches this image
     * if so
     *  - increment the number of pages by 1 and
     *  - add the page to the pageList
     *
     *  Could increase the speed by stopping once a match is found
     *  and moving to the next page
     *
     * @param src An ArrayList of Html objects
     */
    private void imageSearch(ArrayList<Html> src) {
        // loop through the Html objects
        for(Html page : src) {

            // Temp List for improved readability
            ArrayList<Image> images = page.getImages();

            // loop through the corresponding images
            for(Image i : images) {
                if(i.getName().equals(this.fileName)) {
                    this.numPagesDisplayed++;
                    this.pageList.add(page.getLocalPath());
                }
            }
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @return The file name of this image
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Changes the name of this image
     *
     * @param fileName The new name of this image
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @return The number of pages this image is displayed on
     */
    public int getNumPagesDisplayed() {
        return numPagesDisplayed;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Setter to set the number of pages this image is displayed on
     *
     * @param numPagesDisplayed The new number of pages this image is
     *                          displayed on
     */
    public void setNumPagesDisplayed(int numPagesDisplayed) {
        this.numPagesDisplayed = numPagesDisplayed;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @return A list of the filenames of the HTML pages this image is
     *         displayed on
     */
    public ArrayList<String> getPageList() {
        return pageList;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Sets the list of pages to a new list of pages this image is
     * displayed on
     *
     * @param pageList A new list of pages this image is displayed on
     */
    public void setPageList(ArrayList<String> pageList) {
        this.pageList = pageList;
    }
}
