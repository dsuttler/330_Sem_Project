package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 *	The JsonHtml class is used to output to an object for the JsonBuilder
 *  class that contains all the required information for an HTML object
 */

public class JsonHtml {
    private String fileName;
    private int numExternalImages = 0;
    private int numLocalImages = 0; // # of local images
    private int numScriptsRef; // number of JS scripts referenced
    private int numIntraPageLinks = 0; // # of Intra-Page links
    private int numInterSiteLinks = 0; // # of Inter-Site links
    private int numExternalLinks = 0; // # of External links
    private ArrayList<String> cssUtilized = new ArrayList<>(); // list of CSS stylesheets used
    private ArrayList<String> allImages = new ArrayList<>(); // list of the local images
    private ArrayList<String> scriptsUtilized = new ArrayList<>(); // list of the JS scripts used

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Default Constructor
     */
    public JsonHtml() {
        this.numScriptsRef = 0;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Non-Default Constructor
     *
     * @param src The input HTML Object that JsonHtml will
     *            pull the required data from.
     */
    public JsonHtml(Html src) {
        this.fileName = src.getLocalPath();
        this.numScriptsRef = src.getJavascripts().size();
        this.cssUtilized = generateCssList(src.getStyleSheets());
        this.allImages = generateImageList(src.getImages());
        this.scriptsUtilized = generateJSList(src.getJavascripts());
        generateLinkCount(src.getLinks());
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * This function extracts the names of each Css stylesheet from a list
     * of CSS objects
     *
     * @param src A list of Css objects of which to extract the names from
     * @return A list of the names of each of the CSS objects
     */
    public ArrayList<String> generateCssList(ArrayList<Css> src) {
        ArrayList<String> names = new ArrayList<>();
        for(Css i : src) {
            names.add(i.getName());
        }
        return names;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * This function extracts the names of each JS script from a list
     * of Javascript objects
     *
     * JS -> JavaScript
     *
     * @param src A list of JS objects of which to extract the names from
     * @return A list of the names of each of the JS objects
     */
    public ArrayList<String> generateJSList(ArrayList<Javascript> src) {
        ArrayList<String> names = new ArrayList<>();
        for(Javascript i : src) {
            names.add(i.getName());
        }
        return names;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * This function extracts the names of each Image from a list
     * of Image objects
     *
     * @param src A list of Image objects of which to extract the names from
     * @return A list of the names of each of the Image objects
     */
    public ArrayList<String> generateImageList(ArrayList<Image> src) {
        ArrayList<String> names = new ArrayList<>();
        for(Image i : src) {
            names.add(i.getName());
            if(i.getInternal()){
                numLocalImages++;
            }
            else {
                numExternalImages++;
            }
        }
        return names;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Takes in an HTML object and looks through the ArrayList of links
     * to then count the number of Inter-Site, Intra-Site, and External
     * links
     *
     * @param src The HTML Object that contains the ArrayList of Links
     */
    public void generateLinkCount(ArrayList<Link> src) {
        for(Link currentLink : src) {
            switch (currentLink.getClassification()) {
                case "external": this.numExternalLinks += 1;
                break;

                case "intra": this.numIntraPageLinks += 1;
                break;

                case "inter": this.numInterSiteLinks += 1;
                break;
            }
        }
    }


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                     Getters & Setters                             *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @return The name of the html file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @param fileName The new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @return The number of external images
     */
    public int getNumExternalImages() {
        return numExternalImages;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * @param numExternalImages The new number of external images
     */
    public void setNumExternalImages(int numExternalImages) {
        this.numExternalImages = numExternalImages;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the number of local images
     *
     * @return The number of local images on the html page
     */
    public int getNumLocalImages() {
        return this.numLocalImages;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Set the number of local images
     *
     * @param n The new number of local images
     */
    public void setNumLocalImages(int n) {
        this.numLocalImages = n;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the number of scripts referenced by this HTML page
     *
     * @return The number of scripts referenced on this HTML page
     */
    public int getNumScriptsRef() {
        return this.numScriptsRef;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Set the number of scripts referenced
     *
     * @param numScriptsRef The new number of scripts referenced by this
     *                      HTML page
     */
    public void setNumScriptsRef(int numScriptsRef) {
        this.numScriptsRef = numScriptsRef;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the number of links that reference this HTML document
     *
     * @return The number of Intra-Page links inside this HTML Page
     */
    public int getNumIntraPageLinks() {
        return this.numIntraPageLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Set the number of Intra-Page links
     *
     * @param numIntraPageLinks The new number of Intra-Page links
     */
    public void setNumIntraPageLinks(int numIntraPageLinks) {
        this.numIntraPageLinks = numIntraPageLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the number of links in this HTML document that reference
     * anywhere else on this website
     *
     * @return The number of inter site links.
     */
    public int getNumInterSiteLinks() {
        return this.numInterSiteLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Set the number of Inter-Site links
     *
     * @param numInterSiteLinks The new number of Inter-Site links
     */
    public void setNumInterSiteLinks(int numInterSiteLinks) {
        this.numInterSiteLinks = numInterSiteLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the number of links on this document that reference pages that
     * are not a port of this website
     *
     * @return The number of links external to this site
     */
    public int getNumExternalLinks() {
        return this.numExternalLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Set the number of external links
     *
     * @param numExternalLinks The new number of external links
     */
    public void setNumExternalLinks(int numExternalLinks) {
        this.numExternalLinks = numExternalLinks;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the list of all CSS scripts utilized by this HTML document
     *
     * @return The list of CSS scripts utilized on this page
     */
    public ArrayList<String> getCssUtilized() {
        return this.cssUtilized;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Sets cssUtilized with a new ArrayList containing CSS objects
     *
     * @param cssUtilized A new ArrayList of CSS objects
     */
    public void setCssUtilized(ArrayList<String> cssUtilized) {
        this.cssUtilized = cssUtilized;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get the list of images contained on this HTML page
     *
     * @return The list of Images on this page
     */
    public ArrayList<String> getAllImages() {
        return this.allImages;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Sets localImages with a new ArrayList of Image objects that are
     * on this HTML page
     *
     * @param allImages A new ArrayList of Image objects that contain
     *                    images local to the HTML page
     */
    public void setAllImages(ArrayList<String> allImages) {
        this.allImages = allImages;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Get a list of JavaScript objects used by this HTML page
     *
     * @return The list of scripts called on this page
     */
    public ArrayList<String> getScriptsUtilized() {
        return this.scriptsUtilized;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Sets scripts to a new ArrayList of JavaScript objects that are used
     * by this HTML page
     *
     * @param scriptsUtilized A new ArrayList of JavaScript objects that are
     *                        utilized on the page
     */
    public void setScriptsUtilized(ArrayList<String> scriptsUtilized) {
        this.scriptsUtilized = scriptsUtilized;
    }


}
