package edu.odu.cs.cs350;

import java.util.ArrayList;

/**
 * JsonBuilder will collect all the JsonHtml, imageList,
 * and frList objects into one place so that we can write
 * to a json file
 */
public class JsonBuilder {
    private ArrayList<JsonHtml> htmlList;
    private ArrayList<JsonImage> imageList;
    private ArrayList<FileResource> fileResourceList;

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Default Constructor
     * <p>
     * Initializes all 3 lists to be empty
     */
    public JsonBuilder() {
        htmlList = new ArrayList<>();
        imageList = new ArrayList<>();
        fileResourceList = new ArrayList<>();
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Non Default Constructor
     * <p>
     * Takes a website object as an input and builds the htmlList,
     * imageList, and frList
     *
     * @param src The website we are analyzing
     */
    public JsonBuilder(Website src) {
        htmlList = new ArrayList<>();
        imageList = new ArrayList<>();
        fileResourceList = new ArrayList<>();
        ArrayList<Html> htmlListToAdd = src.getAllPages();

        /**
         * Looping through all pages and creating a JsonHtml object out
         * of each one
         */
        for (Html current : htmlListToAdd) {
            JsonHtml toAdd = new JsonHtml(current); // Creating the JsonHtml objects
            htmlList.add(toAdd);
            generateFRList(current);
            generateImageList(htmlListToAdd, current);
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Loops through the list of images of each Html object to determine
     * if the image has already been converted to a JsonImage and added
     * to the imageList. If not it will add it. If it has already added it,
     * the loop will move on
     *
     * @param htmlListToSearch The list of HTML objects that will be
     *                         searched for all occurrences of the image
     * @param current          The Html object we are getting the list of Image
     *                         objects from
     */
    private void generateImageList(ArrayList<Html> htmlListToSearch, Html current) {
        for (Image xImage : current.getImages()) {
            if (!containsImage(xImage)) {
                JsonImage ImageAdded = new JsonImage(xImage.getName(), htmlListToSearch);
                imageList.add(ImageAdded);
            }
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * loops through the list of Html objects in the website and creates
     * a JsonHtml object out of each one
     *
     * @param current The Html object we are getting the list of
     *                File Resources from
     */
    private void generateFRList(Html current) {
        for (FileResource xFileRes : current.getFileResouces()) {
            if (!fileResourceList.contains(xFileRes)) {
                fileResourceList.add(xFileRes);
            }
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Checking to see if the image has already been converted into a
     * JsonImage object and inserted into the list
     *
     * @param src The image we are searching for
     * @return True if the has already been converted to a JsonImage
     * and added to the imageList.
     * False if the image has not been converted and inserted
     */
    public boolean containsImage(Image src) {
        for (JsonImage current : imageList) {
            if (src.getName().equals(current.getFileName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @return A list of Html objects
     */
    public ArrayList<JsonHtml> getHtmlList() {
        return htmlList;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @param htmlList Replaces the internal list of JsonHtml objects with
     *                 a new list
     */
    public void setHtmlList(ArrayList<JsonHtml> htmlList) {
        this.htmlList = new ArrayList<>(htmlList);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @return A list of JsonImage objects
     */
    public ArrayList<JsonImage> getImageList() {
        return imageList;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @param imageList Replaces the internal list of JsonImage objects
     *                  with a new one
     */
    public void setImageList(ArrayList<JsonImage> imageList) {
        this.imageList = new ArrayList<>(imageList);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @return A list of FileResource objects
     */
    public ArrayList<FileResource> getFileResourceList() {
        return fileResourceList;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * @param fileResourceList Replace the internal FileResource object list with
     *               a new one
     */
    public void setFileResourceList(ArrayList<FileResource> fileResourceList) {
        this.fileResourceList = new ArrayList<>(fileResourceList);
    }
}
